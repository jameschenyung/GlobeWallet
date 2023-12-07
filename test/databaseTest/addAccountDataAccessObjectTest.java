package databaseTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import database.addAccountDataAccessObject;

/**
 * Unit tests for the addAccountDataAccessObject class.
 */
class addAccountDataAccessObjectTest {

    /**
     * Test for the saveAccount method.
     */
    @Test
    void saveAccount() {
        addAccountDataAccessObject dao = new addAccountDataAccessObject();

        // Make sure to change number to accommodate with repeating IDs
        Integer accountId = 100;
        Integer userId = 100;
        double balance = 1000.0;
        String currencyType = "USD";

        assertDoesNotThrow(() -> dao.saveAccount(accountId, userId, balance, currencyType));
    }

    /**
     * Test for the generateBalance method.
     */
    @Test
    void generateBalance() {
        addAccountDataAccessObject dao = new addAccountDataAccessObject();
        double generatedBalance = dao.generateBalance();

        assertTrue(generatedBalance >= 1.0 && generatedBalance <= 1000000.0);
    }

    /**
     * Test for the getCurrentUserId method.
     */
    @Test
    void getCurrentUserId() {
        addAccountDataAccessObject dao = new addAccountDataAccessObject();
        assertNull(dao.getCurrentUserId());
    }

    /**
     * Test for the isValidAccount method.
     */
    @Test
    void isValidAccount() {
        addAccountDataAccessObject dao = new addAccountDataAccessObject();

        Integer accountId = 7;

        assertTrue(dao.isValidAccount(accountId));
    }

    /**
     * Test for the isValidCurrency method.
     */
    @Test
    void isValidCurrency() {
        addAccountDataAccessObject dao = new addAccountDataAccessObject();

        String currency = "USD";

        assertTrue(dao.isValidCurrency(currency));
    }

    /**
     * Test for the getUserBankAccounts method.
     */
    @Test
    void getUserBankAccounts() {
        addAccountDataAccessObject dao = new addAccountDataAccessObject();

        Integer currentUserId = 1;

        assertFalse(dao.getUserBankAccounts(currentUserId).isEmpty());
    }

    /**
     * Test for the getAccount method.
     */
    @Test
    void getAccount() {
        addAccountDataAccessObject dao = new addAccountDataAccessObject();

        Integer accountId = 1;

        assertNotNull(dao.getAccount(accountId.toString()));
    }

    /**
     * Test for the getAccountBalance method.
     */
    @Test
    void getAccountBalance() {
        addAccountDataAccessObject dao = new addAccountDataAccessObject();

        Integer accountId = 1;

        assertNotNull(dao.getAccountBalance(accountId));
    }
}

