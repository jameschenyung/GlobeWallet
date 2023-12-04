package use_case.login;
/**
 * Data class that encapsulates the input data required for the login process.
 * This class holds the necessary credentials, such as username and password,
 * required to authenticate a user.
 */
public class LoginInputData {
    private String username;
    private String password;

    /**
     * Constructs a LoginInputData instance with specified username and password.
     *
     * @param username The username of the user attempting to log in.
     * @param password The password of the user.
     */
    public LoginInputData(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    /**
     * Gets the username of the user attempting to log in.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

