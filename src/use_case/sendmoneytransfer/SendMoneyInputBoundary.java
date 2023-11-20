package use_case.sendmoneytransfer;

public interface SendMoneyInputBoundary {
    void sendMoney(SendMoneyInputData inputData);

    void login(SendMoneyInputData sendMoneyInputData);
}
