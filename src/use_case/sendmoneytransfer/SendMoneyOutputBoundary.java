package use_case.sendmoneytransfer;

public interface SendMoneyOutputBoundary {
    void prepareSuccessCheckBalance(SendMoneyOutputData outputData);
    void prepareSuccessConvert(SendMoneyOutputData outputData);
    void prepareSuccessTransfer(SendMoneyOutputData outputData);
    void prepareFailView(String message);
}