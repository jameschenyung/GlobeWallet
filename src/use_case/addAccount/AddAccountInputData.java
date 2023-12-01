package use_case.addAccount;



public class AddAccountInputData {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String currencyType;
    private String userId;

    public AddAccountInputData(String accountNumber, String accountHolderName, double balance, String currencyType, String userId) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.currencyType = currencyType;
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public int getUserId() {
        return userId;
    }
}
