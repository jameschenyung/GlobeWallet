package use_case.sendmoneytransfer;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import interface_adapter.EmailSender.EmailSenderGateway;

import java.util.Objects;

/**
 * Interactor class for handling the process of sending money transactions.
 * This class manages the business logic associated with sending money, including checking account validity,
 * currency conversion, and executing the transfer. It interacts with data access layers, conversion gateways,
 * and communicates results back to the output boundary.
 */
public class SendMoneyInteractor implements SendMoneyInputBoundary {
    private final SendMoneyUserDataAccessInterface userDataAccess;
    private final SendMoneyOutputBoundary outputBoundary;
    private final CurrencyConversionGateway conversionGateway;
    private final EmailSenderGateway emailSenderGateway;

    /**
     * Constructs a SendMoneyInteractor with the specified data access, output boundary,
     * and currency conversion gateway.
     *
     * @param userDataAccess     The data access interface for retrieving and updating account data.
     * @param outputBoundary     The output boundary for presenting results to the user or the system.
     * @param conversionGateway  The gateway for handling currency conversions.
     */
    public SendMoneyInteractor(SendMoneyUserDataAccessInterface userDataAccess,
                               SendMoneyOutputBoundary outputBoundary,
                               CurrencyConversionGateway conversionGateway,
                               EmailSenderGateway emailSenderGateway) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
        this.conversionGateway = conversionGateway;
        this.emailSenderGateway = emailSenderGateway;
    }

    /**
     * Checks the validity of the accounts involved in the transaction.
     *
     * @param sendMoneyInputData The input data containing details of the sender and receiver.
     */
    @Override
    public void checkAccount(SendMoneyInputData sendMoneyInputData) {

        if (userDataAccess.getAccountById(sendMoneyInputData.getSenderId()) == null ||
                userDataAccess.getAccountById(sendMoneyInputData.getReceiverId()) == null) {
            outputBoundary.prepareFailView("One of the accounts is invalid.");
        } else {
            String senderCurrency = userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId());
            String receiverCurrency = userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId());
            outputBoundary.prepareSuccessCheckBalance(new SendMoneyOutputData(true, "Account check successful.",
                    sendMoneyInputData.getSenderId(), senderCurrency,
                    sendMoneyInputData.getReceiverId(), receiverCurrency,
                    null, sendMoneyInputData.getAmount(), null));
        }
    }

    /**
     * Handles the conversion of currency for the transaction.
     *
     * @param sendMoneyInputData The input data containing transaction details and amount to convert.
     */
    @Override
    public void convert(SendMoneyInputData sendMoneyInputData) {
        double amountToSend;
        if (Objects.equals(userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()))){
            amountToSend = sendMoneyInputData.getAmount();
        } else {
            amountToSend = conversionGateway.convertCurrency(
                    userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                    userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),
                    sendMoneyInputData.getAmount()
            );
        }

        if (userDataAccess.getAccountBalance(sendMoneyInputData.getSenderId()) < amountToSend) {
            outputBoundary.prepareFailView("Insufficient funds for the transfer.");
        } else {
            outputBoundary.prepareSuccessConvert(new SendMoneyOutputData(true, "Conversion successful.",
                    sendMoneyInputData.getSenderId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),
                    sendMoneyInputData.getReceiverId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                    amountToSend, sendMoneyInputData.getAmount(), null));
            // Assuming prepareSuccessConvert method and SendMoneyOutputData constructor are implemented correctly
            // Additional steps to complete the transfer would go here, e.g., updating account balances
        }
    }

    /**
     * Executes the transfer of money from the sender to the receiver.
     *
     * @param sendMoneyInputData The input data containing details of the transaction.
     */
    @Override
    public void transfer(SendMoneyInputData sendMoneyInputData) {
        double convertedAmount = conversionGateway.convertCurrency(
                userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),
                sendMoneyInputData.getAmount()
        );

        try {
            userDataAccess.updateAccountBalance(sendMoneyInputData.getSenderId(),
                    userDataAccess.getAccountBalance(sendMoneyInputData.getSenderId()) - convertedAmount);
            Integer id = userDataAccess.generateUniqueTransactionId();
            userDataAccess.createTransaction(id, sendMoneyInputData.getSenderId(), sendMoneyInputData.getReceiverId(),
                    sendMoneyInputData.getAmount(), sendMoneyInputData.getSecurityCode(), 0);
            outputBoundary.prepareSuccessTransfer(new SendMoneyOutputData(true, "Transfer successful.",
                    sendMoneyInputData.getSenderId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),
                    sendMoneyInputData.getReceiverId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                    convertedAmount, sendMoneyInputData.getAmount(), id));

            String senderCurrency = userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId());
            String receiverCurrency = userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId());
            Double amount = sendMoneyInputData.getAmount();
            String senderName = userDataAccess.getFullName(userDataAccess.getUserIdbyAccountId(sendMoneyInputData.getSenderId()));
            String receiverName = userDataAccess.getFullName(userDataAccess.getUserIdbyAccountId(sendMoneyInputData.getReceiverId()));
            String senderEmail = userDataAccess.getEmail(userDataAccess.getUserIdbyAccountId(sendMoneyInputData.getSenderId()));
            String receiverEmail = userDataAccess.getEmail(userDataAccess.getUserIdbyAccountId(sendMoneyInputData.getReceiverId()));

            emailSenderGateway.sendTransactionSender(senderEmail, id, amount, senderCurrency, senderName, receiverName);
            emailSenderGateway.sendTransactionReceiver(receiverEmail, id, amount, receiverCurrency, senderName, receiverName);

        } catch (Exception e) {
            outputBoundary.prepareFailView("Transfer failed due to an error.");
        }
    }
}
