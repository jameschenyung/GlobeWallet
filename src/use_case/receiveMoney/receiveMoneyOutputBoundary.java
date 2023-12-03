package use_case.receiveMoney;

import use_case.sendmoneytransfer.SendMoneyOutputData;

public interface receiveMoneyOutputBoundary {
    void presentTransactionDetails(receiveMoneyOutputData outputData);
    void presentTransactionConfirmation(Double amount, String currency, Double updatedBalance);
    void presentError(String message);
}

