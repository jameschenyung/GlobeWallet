package use_case.receiveMoney;

public class receiveMoneyOutputData {
    private final boolean success;
    private final String message;
    private final Integer receiverId;
    private final String receiverCurrencyType;
    private final Double amountReceived;

    public receiveMoneyOutputData(boolean success, String message, Integer receiverId,
                                  String receiverCurrencyType, Double amountReceived) {
        this.success = success;
        this.message = message;
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

    public String getReceiverCurrencyType() {
        return receiverCurrencyType;
    }

    public Double getAmountReceived() {
        return amountReceived;
    }
}

