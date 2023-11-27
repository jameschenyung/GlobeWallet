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

    public SendMoneyOutputData(boolean success, String message, Integer senderId,
                               String senderCurrencyType, Integer receiverId, String receiverCurrencyType,
                               Double sendSendsAmount, Double receiverReceivesAmount) {
        this.success = success;
        this.message = message;
        this.senderId = senderId;
        this.senderCurrencyType = senderCurrencyType;
        this.receiverId = receiverId;
        this.receiverCurrencyType = receiverCurrencyType;
        this.sendSendsAmount = sendSendsAmount;
        this.receiverReceivesAmount = receiverReceivesAmount;
    }
}