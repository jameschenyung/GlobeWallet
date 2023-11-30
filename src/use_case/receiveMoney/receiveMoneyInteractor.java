package use_case.receiveMoney;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import objects.Account;
import use_case.sendmoneytransfer.SendMoneyOutputData;

public class receiveMoneyInteractor implements receiveMoneyInputBoundary {
    private final receiveMoneyDataAccessInterface userDataAccess;
    private final receiveMoneyOutputBoundary outputBoundary;
    private final CurrencyConversionGateway conversionGateway;

    public receiveMoneyInteractor(receiveMoneyDataAccessInterface userDataAccess,
                                  receiveMoneyOutputBoundary outputBoundary,
                                  CurrencyConversionGateway conversionGateway) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
        this.conversionGateway = conversionGateway;
    }


    @Override
    public void receive(receiveMoneyInputData receiveMoneyInputData) {
        double convertedAmount = conversionGateway.convertCurrency(
                userDataAccess.getCurrencyByAccount(receiveMoneyInputData.getSenderId()),
                userDataAccess.getCurrencyByAccount(receiveMoneyInputData.getReceiverId()),
                receiveMoneyInputData.getAmount()
        );

        try {
            // Update the receiver's account balance
            double newBalance = userDataAccess.getAccountBalance(receiveMoneyInputData.getReceiverId()) + convertedAmount;
            userDataAccess.updateAccountBalance(receiveMoneyInputData.getReceiverId(), newBalance);

            // Prepare a success view with relevant data
            outputBoundary.prepareSuccessTransfer(new receiveMoneyOutputData(true, "Funds received successfully.",
                    receiveMoneyInputData.getReceiverId(), userDataAccess.getCurrencyByAccount(receiveMoneyInputData.getReceiverId()),
                    receiveMoneyInputData.getAmount()));
        } catch (Exception e) {
            outputBoundary.prepareFailView("Failed to receive funds due to an error.");
        }
    }

}
