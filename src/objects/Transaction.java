package objects;

public class Transaction {
    private String SenderId;
    private String ReceiverId;
    private String TransactionId;
    private Double amount;
    private Integer SecurityCode;

    public Transaction(String TransactionId, String SenderId, String ReceiverId,
                       Double amount, Integer SecurityCode){
        this.TransactionId = TransactionId;
        this.SenderId = SenderId;
        this.ReceiverId = ReceiverId;
        this.amount = amount;
        this.SecurityCode = SecurityCode;
    }

    public String getTransactionId(){
        return TransactionId;
    }

    public String getSenderId(){
        return SenderId;
    }

    public String getReceiverId(){
        return ReceiverId;
    }
    public Double getAmount(){return amount;}
}
