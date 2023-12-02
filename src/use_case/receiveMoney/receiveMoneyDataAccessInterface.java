package use_case.receiveMoney;
import objects.Account;
import objects.Transaction;
import java.sql.SQLException;

public interface receiveMoneyDataAccessInterface {
    /**
     * get the current user's id
     * @return the id of the current user
     */
    Integer getCurrentUserId();

    /**
     * Update the balance of an account.
     * @param accountId The ID of the account.
     * @param newBalance The updated balance.
     */
    void updateAccountBalance(Integer accountId, double newBalance) throws SQLException;

    /**
     * Return whether the securityCode matches the one stored in the database.
     * @param securityCode
     * @return True if the securityCode matches, False if it does not.
     */
    boolean validateSecurityCode(Integer securityCode);
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
}
