package use_case.receiveMoney;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class receiveMoneyOutputDataTest {

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
