package use_case.addAccount;
import objects.Account;

import java.sql.SQLException;

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
}

