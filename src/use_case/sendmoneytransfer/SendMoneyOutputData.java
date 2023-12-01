package use_case.sendmoneytransfer;

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