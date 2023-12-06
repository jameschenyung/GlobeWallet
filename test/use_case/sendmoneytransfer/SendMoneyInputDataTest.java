package use_case.sendmoneytransfer;

import org.junit.Test;
import static org.junit.Assert.*;

public class SendMoneyInputDataTest {

    @Test
    public void getSenderId() {
        Integer senderId = 1;
        SendMoneyInputData inputData = new SendMoneyInputData(senderId, 2, 100.0, 1234);
        assertEquals(senderId, inputData.getSenderId());
    }

    @Test
    public void setSenderId() {
        Integer senderId = 1;
        SendMoneyInputData inputData = new SendMoneyInputData(null, 2, 100.0, 1234);
        inputData.setSenderId(senderId);
        assertEquals(senderId, inputData.getSenderId());
    }

    @Test
    public void getReceiverId() {
        Integer receiverId = 2;
        SendMoneyInputData inputData = new SendMoneyInputData(1, receiverId, 100.0, 1234);
        assertEquals(receiverId, inputData.getReceiverId());
    }

    @Test
    public void setReceiverId() {
        Integer receiverId = 2;
        SendMoneyInputData inputData = new SendMoneyInputData(1, null, 100.0, 1234);
        inputData.setReceiverId(receiverId);
        assertEquals(receiverId, inputData.getReceiverId());
    }

    @Test
    public void getAmount() {
        double amount = 100.0;
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, amount, 1234);
        assertEquals(amount, inputData.getAmount(), 0.0);
    }

    @Test
    public void setAmount() {
        double amount = 100.0;
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 0.0, 1234);
        inputData.setAmount(amount);
        assertEquals(amount, inputData.getAmount(), 0.0);
    }

    @Test
    public void getSecurityCode() {
        Integer securityCode = 1234;
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 100.0, securityCode);
        assertEquals(securityCode, inputData.getSecurityCode());
    }

    @Test
    public void setSecurityCode() {
        Integer securityCode = 1234;
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 100.0, null);
        inputData.setSecurityCode(securityCode);
        assertEquals(securityCode, inputData.getSecurityCode());
    }
}