package use_case.addAccount;

import Entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for AddAccount
 */
class AddAccountInteractorTest {

    private AddAccountInteractor interactor;
    private MockAccountDataAccess dataAccess;
    private MockAddAccountOutputBoundary outputBoundary;

    /**
     * Set up the test environment by initializing necessary objects.
     */
    @BeforeEach
    void setUp() {
        dataAccess = new MockAccountDataAccess();
        outputBoundary = new MockAddAccountOutputBoundary();
        interactor = new AddAccountInteractor(dataAccess, outputBoundary);
    }

    /**
     * Test the behavior of adding a valid account.
     */
    @Test
    void addAccount_ValidAccount() {
        AddAccountInputData inputData = new AddAccountInputData(987654, "USD");

        interactor.addAccount(inputData);

        // Assertions to verify the expected behavior
        assertTrue(outputBoundary.isSuccess());
        assertEquals("Account successfully added", outputBoundary.getMessage());
    }

    /**
     * Test the behavior of adding an account with an invalid currency.
     */
    @Test
    void addAccount_InvalidCurrency() {
        AddAccountInputData inputData = new AddAccountInputData(987654, "XYZ");

        interactor.addAccount(inputData);

        // Assertions to verify the expected behavior
        assertFalse(outputBoundary.isSuccess());
        assertEquals("Invalid currency type.", outputBoundary.getMessage());
    }

    /**
     * Test the behavior of adding an account with a number that is already taken.
     */
    @Test
    void addAccount_AccountNumberTaken() {
        AddAccountInputData inputData = new AddAccountInputData(123456, "USD");

        interactor.addAccount(inputData);

        // Assertions to verify the expected behavior
        assertFalse(outputBoundary.isSuccess());
        assertEquals("Account number taken.", outputBoundary.getMessage());
    }

    /**
     * Mock implementation of {@link AccountDataAccessInterface} for testing purposes.
     */
    private static class MockAccountDataAccess implements AccountDataAccessInterface {
        private final ArrayList<Integer> validAccountIds = new ArrayList<>(Arrays.asList(987654));

        @Override
        public void saveAccount(Integer accountId, Integer userId, double balance, String currencyType) {
            // Simulate saving the account
        }

        @Override
        public double generateBalance() {
            return 100.0;
        }

        @Override
        public Integer getCurrentUserId() {
            return 1;
        }

        @Override
        public boolean isValidAccount(Integer accountId) {
            return validAccountIds.contains(accountId);
        }

        @Override
        public boolean isValidCurrency(String currency) {
            return "USD".equals(currency) || "EUR".equals(currency);
        }

        @Override
        public ArrayList<Integer> getUserBankAccounts(Integer currentUserId) {
            return null;
        }

        @Override
        public Account getAccount(String currentUserAccountId) {
            return null;
        }

        @Override
        public Double getAccountBalance(Integer accountId) {
            return null;
        }

    }

    /**
     * Mock implementation of {@link AddAccountOutputBoundary} for testing purposes.
     */
    private static class MockAddAccountOutputBoundary implements AddAccountOutputBoundary {
        private boolean success;
        private String message;

        @Override
        public void presentAddAccountResult(AddAccountOutputData outputData) {
            this.success = outputData.isSuccess();
            this.message = outputData.getMessage();
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
