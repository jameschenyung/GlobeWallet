package objects;

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

    /**
     * Deposits the specified amount into the account.
     * The amount should be positive; otherwise, the deposit is not performed.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    /**
     * Attempts to withdraw the specified amount from the account.
     * Withdrawal is successful only if the amount is positive and less than or equal to the current balance.
     *
     * @param amount The amount to withdraw.
     * @return True if the withdrawal is successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Transfers a specified amount from one account to another.
     * The transfer is successful only if the sender has sufficient balance.
     *
     * @param sender   The account from which the amount will be deducted.
     * @param receiver The account to which the amount will be credited.
     * @param amount   The amount to be transferred.
     */
    public void transfer(Account sender, Account receiver, double amount) {
        if (sender != null && receiver != null && sender.balance >= amount) {
            sender.withdraw(amount);
            receiver.deposit(amount);

            System.out.println("Transfer Success");
        }
        else {
            System.out.println("Transfer Fail");
        }
    }
}