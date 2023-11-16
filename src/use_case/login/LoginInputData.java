package use_case.login;

public class LoginInputData {
    private String username;
    private String password;

    public LoginInputData(String accountID, String password) {
        this.username = accountID;
        this.password = password;
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}

