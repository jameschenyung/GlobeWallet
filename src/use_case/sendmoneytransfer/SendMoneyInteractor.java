package use_case.sendmoneytransfer;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import interface_adapter.EmailSender.EmailSenderGateway;

public class SendMoneyInteractor implements SendMoneyInputBoundary {
    private final SendMoneyUserDataAccessInterface userDataAccess;
    private final SendMoneyOutputBoundary outputBoundary;
    private final CurrencyConversionGateway conversionGateway;
    private final EmailSenderGateway emailSenderGateway;

    public SendMoneyInteractor(SendMoneyUserDataAccessInterface userDataAccess,
                               SendMoneyOutputBoundary outputBoundary,
                               CurrencyConversionGateway conversionGateway,
                               EmailSenderGateway emailSenderGateway) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
        this.conversionGateway = conversionGateway;
        this.emailSenderGateway = emailSenderGateway;
    }

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

    @Override
    public void convert(SendMoneyInputData sendMoneyInputData) {
        double amountToSend = conversionGateway.convertCurrency(
                userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),

                sendMoneyInputData.getAmount()
        );

        if (userDataAccess.getAccountBalance(sendMoneyInputData.getSenderId()) < amountToSend) {
            outputBoundary.prepareFailView("Insufficient funds for the transfer.");
        } else {
            outputBoundary.prepareSuccessConvert(new SendMoneyOutputData(true, "Conversion successful.",
                    sendMoneyInputData.getSenderId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),
                    sendMoneyInputData.getReceiverId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                    amountToSend, sendMoneyInputData.getAmount(), null));
        }
    }

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

            Integer senderId = sendMoneyInputData.getSenderId();
            Integer receiverId = sendMoneyInputData.getReceiverId();

            String senderCurrency = userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId());
            String receiverCurrency = userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId());
            Double amount = sendMoneyInputData.getAmount();
            String senderName = userDataAccess.getFullName(senderId);
            String receiverName = userDataAccess.getFullName(receiverId);
            String senderEmail = userDataAccess.getEmail(senderId);
            String receiverEmail = userDataAccess.getEmail(receiverId);

            emailSenderGateway.sendTransactionSender(senderEmail, id, amount, senderCurrency, senderName, receiverName);
            emailSenderGateway.sendTransactionReceiver(receiverEmail, id, amount, receiverCurrency, senderName, receiverName);

        } catch (Exception e) {
            outputBoundary.prepareFailView("Transfer failed due to an error.");
        }
    }
}
