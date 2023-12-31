package use_case.receiveMoney;
import Entity.Transaction;
import java.sql.SQLException;

public interface receiveMoneyDataAccessInterface {
    /**
     * return if the account is owned by the current user
     * @param accountId account id
     * @return true or false
     */
    boolean accountUnderCurrentUser(Integer accountId);

    /**
     * Update the balance of an account.
     * @param accountId The ID of the account.
     * @param newBalance The updated balance.
     */
    void updateAccountBalance(Integer accountId, double newBalance) throws SQLException;

    /**
     * Return whether the securityCode matches the one stored in the database.
     * @param securityCode security code
     * @param transactionId transaction id
     * @return True if the securityCode matches, False if it does not.
     */
    boolean validateSecurityCode(Integer securityCode, Integer transactionId);
    /**
     * Get the currency type of an account.
     * @param accountId The ID of the account.
     * @return The currency of the account.
     */
    String getCurrencyByAccount(Integer accountId);

    /**
     * Get the current balance of an account.
     * @param accountId The ID of the account.
     * @return The balance of the account.
     */
    Double getAccountBalance(Integer accountId);

    /**
     * Retrieves the transaction details by its ID.
     *
     * @param transactionId The ID of the transaction.
     * @return The transaction details or null if not found.
     */
    Transaction getTransactionDetails(Integer transactionId) throws SQLException;

    /**
     * Get the sender name of the transaction.
     * @param senderId The ID of the receiver.
     * @return The sender of the transaction.
     */
    String getFullName(Integer senderId);

    /**
     * return true or false if a transaction exist in the database and if received is 1
     * @param transactionId transaction id
     * @return true of false
     */
    boolean hasTransaction(Integer transactionId);

    /**
     * get the senderid from transaction with transactionId
     * @param transactionId transaction id
     * @return sender id
     */
    Integer getTransactionSenderId(Integer transactionId);

    /**
     * get the receiverid from transaction with transactionId
     * @param transactionId transaction id
     * @return receiver id
     */
    Integer getTransactionReceiverId(Integer transactionId);

    /**
     * return the amount of transaction
     * @param transactionId transaction
     * @return amount
     */
    double getTransactionAmount(Integer transactionId);

    /**
     * return user id of the user of this account
     * @param accountId accountid
     * @return user id
     */
    Integer getUserIdbyAccountId(Integer accountId);

    /**
     * set received to 1
     * @param transactionId transaction id
     */
    void transactionReceived(Integer transactionId);
}

