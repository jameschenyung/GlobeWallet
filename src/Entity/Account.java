package Entity;

/**
 * Represents a bank account with basic transactional functionalities.
 * This class provides methods to manage an account, including deposit, withdrawal,
 * and transferring funds to another account.
 */
public class Account {
    private Integer accountId;
    private Integer userid;
    private double balance;
    private String CurrencyType;

    /**
     * Constructs an Account with the specified details.
     * Initializes the account with the provided account ID, user ID, initial balance, and currency type.
     * The initial balance is set to 0.0 regardless of the provided balance parameter.
     *
     * @param accountId    The unique identifier for the account.
     * @param userid       The user ID associated with the account.
     * @param balance      The initial balance of the account, which is set to 0.0 in the constructor.
     * @param CurrencyType The type of currency used in the account.
     */
    public Account(Integer accountId, Integer userid, double balance, String CurrencyType) {
        this.accountId = accountId;
        this.userid = userid;
        this.balance = 0.0;
        this.CurrencyType = CurrencyType;
    }

    // Getters for account properties
    public Integer getAccountId() {
        return accountId;
    }

    public Integer getUserid() {
        return userid;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrencyType() {
        return CurrencyType;
    }
}