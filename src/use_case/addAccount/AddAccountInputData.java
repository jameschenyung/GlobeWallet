package use_case.addAccount;
/**
 * Data class that encapsulates the input data required for the "Add Account" use case.
 * This class holds the necessary information to add a new account, including the account number
 * and the currency type.
 */
public class AddAccountInputData {

    private Integer accountNumber;
    private String currencyType;

    /**
     * Constructs an AddAccountInputData instance with specified account number and currency type.
     *
     * @param accountNumber The account number for the new account.
     * @param currencyType  The currency type for the new account.
     */
    public AddAccountInputData(Integer accountNumber, String currencyType) {
        this.accountNumber = accountNumber;
        this.currencyType = currencyType;
    }

    /**
     * Gets the account number associated with this input data.
     *
     * @return The account number.
     */
    public Integer getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the currency type associated with this input data.
     *
     * @return The currency type.
     */
    public String getCurrencyType() {
        return currencyType;
    }

}
