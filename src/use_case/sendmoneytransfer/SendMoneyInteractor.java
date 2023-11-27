package use_case.sendmoneytransfer;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;

public class SendMoneyInteractor implements SendMoneyInputBoundary {
    private final SendMoneyUserDataAccessInterface userDataAccess;
    private final SendMoneyOutputBoundary outputBoundary;
    private final CurrencyConversionGateway conversionGateway;

    public SendMoneyInteractor(SendMoneyUserDataAccessInterface userDataAccess,
                               SendMoneyOutputBoundary outputBoundary,
                               CurrencyConversionGateway conversionGateway) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
        this.conversionGateway = conversionGateway;
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
                    null, sendMoneyInputData.getAmount()));
        }
    }

    @Override
    public void convert(SendMoneyInputData sendMoneyInputData) {
        double amountToSend = conversionGateway.convertCurrency(
                userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),
                userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                sendMoneyInputData.getAmount()
        );

        if (userDataAccess.getAccountBalance(sendMoneyInputData.getSenderId()) < amountToSend) {
            outputBoundary.prepareFailView("Insufficient funds for the transfer.");
        } else {
            outputBoundary.prepareSuccessConvert(new SendMoneyOutputData(true, "Conversion successful.",
                    sendMoneyInputData.getSenderId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),
                    sendMoneyInputData.getReceiverId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                    amountToSend, sendMoneyInputData.getAmount()));
        }
    }

    @Override
    public void transfer(SendMoneyInputData sendMoneyInputData) {
        double convertedAmount = conversionGateway.convertCurrency(
                userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),
                userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                sendMoneyInputData.getAmount()
        );

        try {
            userDataAccess.updateAccountBalance(sendMoneyInputData.getSenderId(),
                    userDataAccess.getAccountBalance(sendMoneyInputData.getSenderId()) - convertedAmount);
            outputBoundary.prepareSuccessTransfer(new SendMoneyOutputData(true, "Transfer successful.",
                    sendMoneyInputData.getSenderId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getSenderId()),
                    sendMoneyInputData.getReceiverId(), userDataAccess.getCurrencyByAccount(sendMoneyInputData.getReceiverId()),
                    convertedAmount, sendMoneyInputData.getAmount()));
        } catch (Exception e) {
            outputBoundary.prepareFailView("Transfer failed due to an error.");
        }
    }
}
