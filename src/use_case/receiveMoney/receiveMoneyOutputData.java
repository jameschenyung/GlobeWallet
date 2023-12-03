package use_case.receiveMoney;
/**
 * Data class that encapsulates the output data for the money receiving operation.
 * This class holds information about the outcome of a transaction, including success status,
 * message, details about the sender and receiver, the currency type, and the amount received.
 */

public class receiveMoneyOutputData {
    private final boolean success;
    private final String message;
    private final String senderName;
    private final Integer receiverId;
    private final String receiverCurrencyType;
    private final Double amountReceived;

    /**
     * Constructs a receiveMoneyOutputData instance with specified transaction details.
     *
     * @param success              Indicates whether the transaction was successful.
     * @param message              A message describing the outcome of the transaction.
     * @param senderName           The name of the sender in the transaction.
     * @param receiverId           The ID of the receiver in the transaction.
     * @param receiverCurrencyType The currency type of the receiver's account.
     * @param amountReceived       The amount of money received in the transaction.
     */
    public receiveMoneyOutputData(boolean success, String message, String senderName, Integer receiverId,
                                  String receiverCurrencyType, Double amountReceived) {
        this.success = success;
        this.message = message;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.receiverCurrencyType = receiverCurrencyType;
        this.amountReceived = amountReceived;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getReceiverCurrencyType() {
        return receiverCurrencyType;
    }

    public Double getAmountReceived() {
        return amountReceived;
    }
}


