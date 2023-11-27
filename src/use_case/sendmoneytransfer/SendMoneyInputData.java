package use_case.sendmoneytransfer;

public class SendMoneyInputData {
    private Integer senderId;
    private Integer receiverId;
    private double amount;
    private Integer securityCode;

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



