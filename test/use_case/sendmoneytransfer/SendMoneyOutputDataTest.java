package use_case.sendmoneytransfer;

import org.junit.Test;
import static org.junit.Assert.*;

public class SendMoneyOutputDataTest {

    @Test
    public void isSuccess() {
        boolean success = true;
        SendMoneyOutputData outputData = new SendMoneyOutputData(success, "", 1, "USD", 2, "EUR", 100.0, 110.0, 123);

        assertEquals(success, outputData.isSuccess());
    }

    @Test
    public void getSenderId() {
        int senderId = 1;
        SendMoneyOutputData outputData = new SendMoneyOutputData(true, "", senderId, "USD", 2, "EUR", 100.0, 110.0, 123);

        assertEquals(senderId, (int) outputData.getSenderId());
    }

    @Test
    public void getMessage() {
        String message = "Transaction successful";
        SendMoneyOutputData outputData = new SendMoneyOutputData(true, message, 1, "USD", 2, "EUR", 100.0, 110.0, 123);

        assertEquals(message, outputData.getMessage());
    }

    @Test
    public void getReceiverId() {
        int receiverId = 2;
        SendMoneyOutputData outputData = new SendMoneyOutputData(true, "", 1, "USD", receiverId, "EUR", 100.0, 110.0, 123);

        assertEquals(receiverId, (int) outputData.getReceiverId());
    }

    @Test
    public void getSenderCurrencyType() {
        String senderCurrency = "USD";
        SendMoneyOutputData outputData = new SendMoneyOutputData(true, "", 1, senderCurrency, 2, "EUR", 100.0, 110.0, 123);

        assertEquals(senderCurrency, outputData.getSenderCurrencyType());
    }

    @Test
    public void getReceiverCurrencyType() {
        String receiverCurrency = "EUR";
        SendMoneyOutputData outputData = new SendMoneyOutputData(true, "", 1, "USD", 2, receiverCurrency, 100.0, 110.0, 123);

        assertEquals(receiverCurrency, outputData.getReceiverCurrencyType());
    }

    @Test
    public void getSendSendsAmount() {
        double sendAmount = 100.0;
        SendMoneyOutputData outputData = new SendMoneyOutputData(true, "", 1, "USD", 2, "EUR", sendAmount, 110.0, 123);

        assertEquals(sendAmount, outputData.getSendSendsAmount(), 0.0);
    }

    @Test
    public void getReceiverReceivesAmount() {
        double receiveAmount = 110.0;
        SendMoneyOutputData outputData = new SendMoneyOutputData(true, "", 1, "USD", 2, "EUR", 100.0, receiveAmount, 123);

        assertEquals(receiveAmount, outputData.getReceiverReceivesAmount(), 0.0);
    }

    @Test
    public void getTransactionId() {
        int transactionId = 123;
        SendMoneyOutputData outputData = new SendMoneyOutputData(true, "", 1, "USD", 2, "EUR", 100.0, 110.0, transactionId);

        assertEquals(transactionId, (int) outputData.getTransactionId());
    }
}