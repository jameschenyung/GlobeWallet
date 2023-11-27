package objects;

public class Account {
    private Integer accountId;
    private Integer userid;
    private double balance;
    private String CurrencyType;

    public Account(Integer accountId, Integer userid, double balance, String CurrencyType) {
        this.accountId = accountId;
        this.userid = userid;
        this.balance = 0.0;
        this.CurrencyType = CurrencyType;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getUserid() {
        return userid;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

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