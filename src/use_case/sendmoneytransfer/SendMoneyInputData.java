package use_case.sendmoneytransfer;

/**
 * Data class that encapsulates the input data required for processing a send money transaction.
 * This class holds details such as the sender's and receiver's IDs, the amount to be transferred,
 * and the security code associated with the transaction.
 */
public class SendMoneyInputData {
    private Integer senderId;
    private Integer receiverId;
    private double amount;
    private Integer securityCode;

    /**
     * Constructs a SendMoneyInputData instance with specified details for a money transfer.
     *
     * @param senderId      The unique identifier for the sender in the transaction.
     * @param receiverId    The unique identifier for the receiver in the transaction.
     * @param amount        The amount of money to be transferred.
     * @param securityCode  The security code associated with the transaction, if applicable.
     */
    public SendMoneyInputData(Integer senderId, Integer receiverId, double amount, Integer securityCode) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.securityCode = securityCode;
    }

    public Integer getSenderId() {
            return senderId;
        }

    public void setSenderId(Integer senderId) {
            this.senderId = senderId;
        }

    public Integer getReceiverId() {
            return receiverId;
        }

    public void setReceiverId(Integer receiverId) {
            this.receiverId = receiverId;
        }

    public double getAmount() {
            return amount;
        }

    public void setAmount(double amount) {
            this.amount = amount;
        }

    public Integer getSecurityCode() {
            return securityCode;
        }

    public void setSecurityCode(Integer securityCode) {
            this.securityCode = securityCode;
        }
    }



