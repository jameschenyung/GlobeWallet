package use_case.receiveMoney;

import use_case.sendmoneytransfer.SendMoneyOutputData;

public interface receiveMoneyOutputBoundary {
    void presentTransactionDetails(String senderName, Double amount, String currency);
    void presentTransactionConfirmation(Double amount, String currency, Double updatedBalance);
    void presentError(String message);
}

