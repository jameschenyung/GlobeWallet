package use_case.receiveMoney;

import views.ReceiveMoneyPanel;
import use_case.receiveMoney.receiveMoneyDataAccessInterface;

/**
 * Data class that encapsulates the input data required for processing a money receiving transaction.
 * This class holds essential details such as the transaction ID and security code necessary
 * for the completion of a money receiving operation.
 */
public class receiveMoneyInputData {
    private Integer transactionId;
    private Integer securityCode;
    private receiveMoneyDataAccessInterface receiveMoneyDataAccess;

    /**
     * Constructs a receiveMoneyInputData instance with specified transaction ID and security code.
     *
     * @param transactionId The unique identifier for the transaction.
     * @param securityCode The security code associated with the transaction, if applicable.
     */
    public receiveMoneyInputData(Integer transactionId, Integer securityCode) {
        this.transactionId = transactionId;
        this.securityCode = securityCode;
    }

    /**
     * Sets the security code for the transaction.
     *
     * @param securityCode The security code to be associated with the transaction.
     */
    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * Gets the security code of the transaction.
     *
     * @return The security code of the transaction.
     */
    public Integer getSecurityCode() {
        return securityCode;
    }

    /**
     * Sets the transaction ID.
     *
     * @param transactionId The transaction ID to be set.
     */
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the transaction ID.
     *
     * @return The transaction ID.
     */
    public Integer getTransactionId() {
        return transactionId;
    }
}
