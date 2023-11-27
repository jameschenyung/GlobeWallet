package use_case.sendmoneytransfer;

import objects.Account;

import java.sql.SQLException;

public interface SendMoneyUserDataAccessInterface {
    /**
     * Retrieves an account object by accountid.
     * @param accountId The id of the account
     * @return the account object corresponding to the accountId, or null if not found
     */
    Account getAccountById(Integer accountId);
    /**
     * update the balance of the account
     * @param accountId the id of the account
     * @param newBalance the updated balance
     */
    void updateAccountBalance(Integer accountId, double newBalance) throws SQLException;
    /**
     * check if the currency is valid
     * @param currency the currency type being checked
     * @return true or false
     */
    boolean isValidCurrency(String currency);

    /**
     * check if the account is valid
     * @param accountId accountId
     * @return if the account exist
     */
    boolean isValidAccount(Integer accountId);

    /**
     * return the currency type of this account
     * @param accountId account id
     * @return currency account
     */
    String getCurrencyByAccount(Integer accountId);

    Double getAccountBalance(Integer accountId);
}
