package objects;

public class Transaction {
    private Integer SenderId;
    private Integer ReceiverId;
    private Integer TransactionId;
    private Double amount;
    private Integer SecurityCode;
    private Integer received;

    public Transaction(Integer TransactionId, Integer SenderId, Integer ReceiverId,
                       Double amount, Integer SecurityCode, Integer received){
        this.TransactionId = TransactionId;
        this.SenderId = SenderId;
        this.ReceiverId = ReceiverId;
        this.amount = amount;
        this.SecurityCode = SecurityCode;
        this.received = received;
    }

    public Integer getTransactionId(){
        return TransactionId;
    }
    public Integer getSenderId(){
        return SenderId;
    }
    public Integer getReceiverId(){
        return ReceiverId;
    }
    public Double getAmount(){return amount;}
    public Integer isReceived() {
        return received;
    }
}
