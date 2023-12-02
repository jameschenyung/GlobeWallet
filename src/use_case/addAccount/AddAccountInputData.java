package use_case.addAccount;



public class AddAccountInputData {
    private Integer accountNumber;
    private String currencyType;
    private Integer userId;

    public AddAccountInputData(Integer accountNumber, String currencyType, Integer userId) {
        this.accountNumber = accountNumber;
        this.currencyType = currencyType;
        this.userId = userId;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public int getUserId() {
        return userId;
    }
}
