package use_case.receiveMoney;
import objects.Account;
import java.sql.SQLException;

public interface receiveMoneyDataAccessInterface {
    /**
     * Retrieves an account object by account ID.
     * @param accountId The ID of the account.
     * @return The account object corresponding to the accountId, or null if not found.
     */
    Account getAccountById(Integer accountId);

    /**
     * Update the balance of an account.
     * @param accountId The ID of the account.
     * @param newBalance The updated balance.
     */
    void updateAccountBalance(Integer accountId, double newBalance) throws SQLException;

    /**
     * Check if an account ID is valid.
     * @param accountId The ID of the account to check.
     * @return True if the account exists, false otherwise.
     */
    boolean isValidAccount(Integer accountId);

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
}
