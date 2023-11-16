package use_case.login;

public class LoginInputData {
    private String accountID;
    private String password;

    public LoginInputData(String accountID, String password) {
        this.accountID = accountID;
        this.password = password;
    }
    
    public String getAccountID() { return accountID; }
    public String getPassword() { return password; }
    
    public void setAccountID(String accountID) { this.accountID = accountID; }
    public void setPassword(String password) { this.password = password; }
}

