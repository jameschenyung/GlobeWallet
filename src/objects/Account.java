package objects;

class Account {
    private String accountId;
    private User owner;
    private double balance;

    public Account(String accountId, User owner) {
        this.accountId = accountId;
        this.owner = owner;
        this.balance = 0.0;
    }

    public String getAccountId() {
        return accountId;
    }

    public User getOwner() {
        return owner;
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
}

class CheckingAccount extends Account {
    public CheckingAccount(String accountId, User owner) {
        super(accountId, owner);
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(String accountId, User owner) {
        super(accountId, owner);
    }
}
