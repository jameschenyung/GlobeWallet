package use_case.sendmoneytransfer;

/**
 * Data class that encapsulates the output data for the send money operation.
 * This class holds information about the outcome of a money sending transaction, including the success status,
 * message, details of the sender and receiver, the currency types, amounts involved, and the transaction ID.
 */
public class SendMoneyOutputData {
    private final boolean success;
    private final String message;
    private final Integer senderId;
    private final String senderCurrencyType;
    private final Integer receiverId;
    private final String receiverCurrencyType;
    private final Double sendSendsAmount;
    private final Double receiverReceivesAmount;
    private final Integer transactionId;

    /**
     * Constructs a SendMoneyOutputData instance with specified details of the money sending operation.
     *
     * @param success               Indicates whether the transaction was successful.
     * @param message               A message describing the outcome of the transaction.
     * @param senderId              The ID of the sender in the transaction.
     * @param senderCurrencyType    The currency type of the sender's account.
     * @param receiverId            The ID of the receiver in the transaction.
     * @param receiverCurrencyType  The currency type of the receiver's account.
     * @param sendSendsAmount       The amount of money sent by the sender.
     * @param receiverReceivesAmount The amount of money received by the receiver.
     * @param transactionId         The unique identifier for the transaction.
     */
    public SendMoneyOutputData(boolean success, String message, Integer senderId,
                               String senderCurrencyType, Integer receiverId, String receiverCurrencyType,
                               Double sendSendsAmount, Double receiverReceivesAmount, Integer transactionId) {
        this.success = success;
        this.message = message;
        this.senderId = senderId;
        this.senderCurrencyType = senderCurrencyType;
        this.receiverId = receiverId;
        this.receiverCurrencyType = receiverCurrencyType;
        this.sendSendsAmount = sendSendsAmount;
        this.receiverReceivesAmount = receiverReceivesAmount;
        this.transactionId = transactionId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public String getSenderCurrencyType() {
        return senderCurrencyType;
    }

    public String getReceiverCurrencyType() {
        return receiverCurrencyType;
    }

    public Double getSendSendsAmount() {
        return sendSendsAmount;
    }

    public Double getReceiverReceivesAmount() {
        return receiverReceivesAmount;
    }

    public Integer getTransactionId() {
        return transactionId;
    }
}