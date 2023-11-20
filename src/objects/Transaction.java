package objects;

public class Transaction {
    private String SenderId;
    private String ReceiverId;
    private String TransactionId;
    private Integer SecurityCode;

    public Transaction(String TransactionId, String SenderId, String ReceiverId, Integer SecurityCode){
        this.TransactionId = TransactionId;
        this.SenderId = SenderId;
        this.ReceiverId = ReceiverId;
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
}
