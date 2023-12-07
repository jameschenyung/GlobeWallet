package use_case.sendmoneytransfer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for the {@link SendMoneyInputData} class.
 * These tests focus on ensuring the correctness of the SendMoneyInputData class,
 * covering the getter and setter methods for various properties.
 */
public class SendMoneyInputDataTest {
    /**
     * Test the {@link SendMoneyInputData#getSenderId()} method.
     * Verifies that the sender ID is correctly retrieved.
     */
    @Test
    public void getSenderId() {
        Integer senderId = 1;
        SendMoneyInputData inputData = new SendMoneyInputData(senderId, 2, 100.0, 1234);
        assertEquals(senderId, inputData.getSenderId());
    }

    /**
     * Test the {@link SendMoneyInputData#setSenderId(Integer)} method.
     * Verifies that the sender ID is correctly set.
     */
    @Test
    public void setSenderId() {
        Integer senderId = 1;
        SendMoneyInputData inputData = new SendMoneyInputData(null, 2, 100.0, 1234);
        inputData.setSenderId(senderId);
        assertEquals(senderId, inputData.getSenderId());
    }

    /**
     * Test the {@link SendMoneyInputData#getReceiverId()} method.
     * Verifies that the receiver ID is correctly retrieved.
     */
    @Test
    public void getReceiverId() {
        Integer receiverId = 2;
        SendMoneyInputData inputData = new SendMoneyInputData(1, receiverId, 100.0, 1234);
        assertEquals(receiverId, inputData.getReceiverId());
    }

    /**
     * Test the {@link SendMoneyInputData#setReceiverId(Integer)} method.
     * Verifies that the receiver ID is correctly set.
     */
    @Test
    public void setReceiverId() {
        Integer receiverId = 2;
        SendMoneyInputData inputData = new SendMoneyInputData(1, null, 100.0, 1234);
        inputData.setReceiverId(receiverId);
        assertEquals(receiverId, inputData.getReceiverId());
    }

    /**
     * Test the {@link SendMoneyInputData#getAmount()} method.
     * Verifies that the amount is correctly retrieved.
     */
    @Test
    public void getAmount() {
        double amount = 100.0;
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, amount, 1234);
        assertEquals(amount, inputData.getAmount(), 0.0);
    }

    /**
     * Test the {@link SendMoneyInputData#setAmount(double)} method.
     * Verifies that the amount is correctly set.
     */
    @Test
    public void setAmount() {
        double amount = 100.0;
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 0.0, 1234);
        inputData.setAmount(amount);
        assertEquals(amount, inputData.getAmount(), 0.0);
    }

    /**
     * Test the {@link SendMoneyInputData#getSecurityCode()} method.
     * Verifies that the security code is correctly retrieved.
     */
    @Test
    public void getSecurityCode() {
        Integer securityCode = 1234;
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 100.0, securityCode);
        assertEquals(securityCode, inputData.getSecurityCode());
    }

    /**
     * Test the {@link SendMoneyInputData#setSecurityCode(Integer)} method.
     * Verifies that the security code is correctly set.
     */
    @Test
    public void setSecurityCode() {
        Integer securityCode = 1234;
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 100.0, null);
        inputData.setSecurityCode(securityCode);
        assertEquals(securityCode, inputData.getSecurityCode());
    }
}