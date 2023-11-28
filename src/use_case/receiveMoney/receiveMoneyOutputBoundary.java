package use_case.receiveMoney;

import use_case.sendmoneytransfer.SendMoneyOutputData;

public interface receiveMoneyOutputBoundary {
    void prepareSuccessConvert(receiveMoneyOutputData outputData);
    void prepareSuccessTransfer(receiveMoneyOutputData outputData);
    void prepareFailView(String message);
}
