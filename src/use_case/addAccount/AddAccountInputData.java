package use_case.addAccount;



public class AddAccountInputData {
    private Integer accountNumber;
    private String currencyType;

    public AddAccountInputData(Integer accountNumber, String currencyType) {
        this.accountNumber = accountNumber;
        this.currencyType = currencyType;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public String getCurrencyType() {
        return currencyType;
    }

}
