package use_case.receiveMoney;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the {@link receiveMoneyOutputData} class.
 * These tests focus on ensuring the correctness of the receiveMoneyOutputData class,
 * specifically verifying that the getters return the expected values.
 */
class receiveMoneyOutputDataTest {
    /**
     * Test the constructor and getters of the {@link receiveMoneyOutputData} class.
     * Verifies that the instance is constructed with the correct values and the getters return them.
     */
    @Test
    void testReceiveMoneyOutputData() {
        // Sample data for testing
        boolean success = true;
        String message = "Transaction successful";
        String senderName = "John Doe";
        Integer receiverId = 1;
        String receiverCurrencyType = "USD";
        Double amountReceived = 100.0;

        // Create an instance of receiveMoneyOutputData
        receiveMoneyOutputData outputData = new receiveMoneyOutputData(success, message, senderName,
                receiverId, receiverCurrencyType,
                amountReceived);

        // Assertions to verify that the getters return the correct values
        assertEquals(success, outputData.isSuccess());
        assertEquals(message, outputData.getMessage());
        assertEquals(senderName, outputData.getSenderName());
        assertEquals(receiverId, outputData.getReceiverId());
        assertEquals(receiverCurrencyType, outputData.getReceiverCurrencyType());
        assertEquals(amountReceived, outputData.getAmountReceived());
    }
}
