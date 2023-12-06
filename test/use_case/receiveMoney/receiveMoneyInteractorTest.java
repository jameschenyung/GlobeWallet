package use_case.receiveMoney;

import Entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MockReceiveMoneyDataAccess implements receiveMoneyDataAccessInterface {
    // Assuming these are the sample values for testing
    private final int validTransactionId = 12345;
    private final int validUserId = 1;
    private final int validSecurityCode = 67890;
    private final double transactionAmount = 100.0;
    private final String currency = "USD";

    @Override
    public boolean accountUnderCurrentUser(Integer accountId) {
        return accountId == validUserId;
    }

    @Override
    public void updateAccountBalance(Integer accountId, double newBalance) {
        // Simulate updating the account balance
    }

    @Override
    public boolean validateSecurityCode(Integer securityCode, Integer transactionId) {
        return securityCode == validSecurityCode && transactionId == validTransactionId;
    }

    @Override
    public String getCurrencyByAccount(Integer accountId) {
        return currency;
    }

    @Override
    public Double getAccountBalance(Integer accountId) {
        return 200.0; // Simulate an existing balance
    }

    @Override
    public Transaction getTransactionDetails(Integer transactionId) {
        // Simulate retrieving transaction details
        return new Transaction(1, 2, 3, 100.0, 123, 0);
    }

    @Override
    public String getFullName(Integer userId) {
        return "John Doe";
    }

    @Override
    public boolean hasTransaction(Integer transactionId) {
        return transactionId == validTransactionId;
    }

    @Override
    public Integer getTransactionSenderId(Integer transactionId) {
        return 2; // Simulate sender ID
    }

    @Override
    public Integer getTransactionReceiverId(Integer transactionId) {
        return validUserId;
    }

    @Override
    public double getTransactionAmount(Integer transactionId) {
        return transactionAmount;
    }

    @Override
    public Integer getUserIdbyAccountId(Integer accountId) {
        return validUserId;
    }

    @Override
    public void transactionReceived(Integer transactionId) {
        // Simulate marking the transaction as received
    }
}

class MockReceiveMoneyOutputBoundary implements receiveMoneyOutputBoundary {
    private boolean successful;
    private String message;

    @Override
    public void presentTransactionDetails(receiveMoneyOutputData outputData) {
        this.successful = outputData.isSuccess();
        this.message = outputData.getMessage();
    }

    @Override
    public void presentTransactionConfirmation(Double amount, String currency, Double updatedBalance) {
    }

    @Override
    public void presentError(String error) {
        this.successful = false;
        this.message = error;
    }


    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}


class receiveMoneyInteractorTest {

    private receiveMoneyInteractor interactor;
    private MockReceiveMoneyDataAccess dataAccess;
    private MockReceiveMoneyOutputBoundary outputBoundary;

    @BeforeEach
    void setUp() {
        dataAccess = new MockReceiveMoneyDataAccess();
        outputBoundary = new MockReceiveMoneyOutputBoundary();
        interactor = new receiveMoneyInteractor(dataAccess, outputBoundary);
    }

    @Test
    void verifyTransaction_ValidTransaction() {
        receiveMoneyInputData inputData = new receiveMoneyInputData(12345, 123);
        interactor.verifyTransaction(inputData);

        assertTrue(outputBoundary.isSuccessful());
        assertEquals("Successful", outputBoundary.getMessage());
    }

    @Test
    void confirmSecurityCode_ValidCode() {
        receiveMoneyInputData inputData = new receiveMoneyInputData(12345, 67890);
        interactor.confirmSecurityCode(inputData);

        assertTrue(outputBoundary.isSuccessful());
        assertEquals("Transaction confirmed", outputBoundary.getMessage());
    }

    // Additional test cases...
}
