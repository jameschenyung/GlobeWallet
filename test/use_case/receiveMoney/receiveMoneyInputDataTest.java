package use_case.receiveMoney;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the {@link receiveMoneyInputData} class.
 * These tests cover the functionality of the receiveMoneyInputData class,
 * focusing on setting and retrieving the security code and transaction ID.
 */

class receiveMoneyInputDataTest {

    private receiveMoneyInputData inputData;

    /**
     * Set up a new receiveMoneyInputData instance before each test.
     */
    @BeforeEach
    void setUp() {
        inputData = new receiveMoneyInputData(null, null);
    }

    /**
     * Test the {@link receiveMoneyInputData#setSecurityCode(Integer)} and {@link receiveMoneyInputData#getSecurityCode()} methods.
     * Verifies that the security code can be set and retrieved correctly.
     */
    @Test
    void setAndGetSecurityCode() {
        Integer expectedSecurityCode = 1234;
        inputData.setSecurityCode(expectedSecurityCode);
        Integer actualSecurityCode = inputData.getSecurityCode();

        assertEquals(expectedSecurityCode, actualSecurityCode, "The retrieved security code should match the one set.");
    }

    /**
     * Test the {@link receiveMoneyInputData#setTransactionId(Integer)} and {@link receiveMoneyInputData#getTransactionId()} methods.
     * Verifies that the transaction ID can be set and retrieved correctly.
     */
    @Test
    void setAndGetTransactionId() {
        Integer expectedTransactionId = 5678;
        inputData.setTransactionId(expectedTransactionId);
        Integer actualTransactionId = inputData.getTransactionId();

        assertEquals(expectedTransactionId, actualTransactionId, "The retrieved transaction ID should match the one set.");
    }
}
