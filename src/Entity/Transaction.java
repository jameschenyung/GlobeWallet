package Entity;

/**
 * Represents a financial transaction between two parties.
 * This class stores details about a transaction including the sender, receiver, transaction ID,
 * amount, security code, and a flag indicating whether the transaction was received.
 */
public class Transaction {
    private Integer SenderId;
    private Integer ReceiverId;
    private Integer TransactionId;
    private Double amount;
    private Integer SecurityCode;
    private Integer received;

    /**
     * Constructs a Transaction with specified details.
     * Initializes the transaction with the given transaction ID, sender ID, receiver ID,
     * amount, security code, and received status.
     *
     * @param TransactionId The unique identifier for the transaction.
     * @param SenderId      The ID of the sender in the transaction.
     * @param ReceiverId    The ID of the receiver in the transaction.
     * @param amount        The amount of money involved in the transaction.
     * @param SecurityCode  A security code associated with the transaction.
     * @param received      An indicator (e.g., 0 or 1) representing whether the transaction is received.
     */
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
