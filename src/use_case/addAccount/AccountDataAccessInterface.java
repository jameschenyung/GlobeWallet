package use_case.addAccount;
import Entity.Account;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccountDataAccessInterface {
    /**
     * save the account into database
     * @param accountId account id to be stored
     * @param userId user id to be stored
     * @param balance balance to be stored
     * @throws SQLException failure
     */
    void saveAccount(Integer accountId, Integer userId, double balance, String currencyType) throws SQLException;

    /**
     * generate balance for account
     * @return a positive balance
     */
    double generateBalance();

    /**
     * get current user's id
     * @return userid from current user table
     */
    Integer getCurrentUserId();

    /**
     * validate account id inputted
     * @param accountId account id inputted
     * @return true or false
     */
    boolean isValidAccount(Integer accountId);

    /**
     * return true or false if this currency is valid
     * @param currency currencyType
     * @return true or false
     */
    boolean isValidCurrency(String currency);

    /**
     * Retrieves bank accounts for a given user ID.
     * @param currentUserId The user ID for which to retrieve accounts.
     * @return A list of account IDs.
     */
    ArrayList<Integer> getUserBankAccounts(Integer currentUserId);

    Account getAccount(String currentUserAccountId);

    Double getAccountBalance(Integer accountId);
}

