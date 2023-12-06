package use_case.sendmoneytransfer;

import Entity.Account;

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

    /**
     * return account balance
     * @param accountId account id
     * @return account balance of this accountId
     */
    Double getAccountBalance(Integer accountId);

    /**
     * create one Transaction and assign it with a unique id then insert it to the Transaction table
     * @param SendId sender id
     * @param ReceiverId receiver id
     * @param amount transferred amount
     * @param SecurityCode Security code
     */
    void createTransaction(Integer transactionId, Integer SendId, Integer ReceiverId, Double amount, Integer SecurityCode, Integer received);

    /**
     * return a random id for transaction
     * @return random id
     */
    int generateUniqueTransactionId();
    /**
     *
     * @param userId
     * @return email
     */
    String getEmail(int userId);

    /**
     *
     * @param userId
     * @return full name
     */
    String getFullName(Integer userId);

    /**
     * return the user id
     * @param accountId account id
     * @return user id
     */
    public Integer getUserIdbyAccountId(Integer accountId);
}
