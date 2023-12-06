package use_case.receiveMoney;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class receiveMoneyInputDataTest {

    private receiveMoneyInputData inputData;

    @BeforeEach
    void setUp() {
        inputData = new receiveMoneyInputData(null, null);
    }

    @Test
    void setAndGetSecurityCode() {
        Integer expectedSecurityCode = 1234;
        inputData.setSecurityCode(expectedSecurityCode);
        Integer actualSecurityCode = inputData.getSecurityCode();

        assertEquals(expectedSecurityCode, actualSecurityCode, "The retrieved security code should match the one set.");
    }

    @Test
    void setAndGetTransactionId() {
        Integer expectedTransactionId = 5678;
        inputData.setTransactionId(expectedTransactionId);
        Integer actualTransactionId = inputData.getTransactionId();

        assertEquals(expectedTransactionId, actualTransactionId, "The retrieved transaction ID should match the one set.");
    }
}
