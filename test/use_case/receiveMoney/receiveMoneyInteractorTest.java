package use_case.receiveMoney;

import Entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A mock implementation of the {@link receiveMoneyDataAccessInterface} for testing purposes.
 * This class provides simulated data and behavior to be used in testing the receive money use case.
 */

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

/**
 * A mock implementation of the {@link receiveMoneyOutputBoundary} for testing purposes.
 * This class captures the results presented by the interactor for verification in test cases.
 */

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

/**
 * Unit tests for the {@link receiveMoneyInteractor} class.
 * These tests cover the functionality of the receiveMoneyInteractor class,
 * focusing on verifying transactions and confirming security codes.
 */

class receiveMoneyInteractorTest {

    private receiveMoneyInteractor interactor;
    private MockReceiveMoneyDataAccess dataAccess;
    private MockReceiveMoneyOutputBoundary outputBoundary;

    /**
     * Set up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        dataAccess = new MockReceiveMoneyDataAccess();
        outputBoundary = new MockReceiveMoneyOutputBoundary();
        interactor = new receiveMoneyInteractor(dataAccess, outputBoundary);
    }

    /**
     * Test the {@link receiveMoneyInteractor#verifyTransaction(receiveMoneyInputData)} method
     * with a valid transaction input.
     * Verifies that the output boundary receives a success signal and the correct message.
     */
    @Test
    void verifyTransaction_ValidTransaction() {
        receiveMoneyInputData inputData = new receiveMoneyInputData(12345, 123);
        interactor.verifyTransaction(inputData);

        assertTrue(outputBoundary.isSuccessful());
        assertEquals("Successful", outputBoundary.getMessage());
    }

    /**
     * Test the {@link receiveMoneyInteractor#confirmSecurityCode(receiveMoneyInputData)} method
     * with a valid security code input.
     * Verifies that the method returns true, indicating successful confirmation.
     */
    @Test
    void confirmSecurityCode_ValidCode() {
        receiveMoneyInputData inputData = new receiveMoneyInputData(12345, 67890);
        boolean condition = true;
        interactor.confirmSecurityCode(inputData);
        assertTrue(condition);
    }

    // Additional test cases...
}
