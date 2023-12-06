package Entity;

import java.util.*;

/**
 * Represents a user with personal and financial information.
 * This class stores user details such as ID, name, username, password, and associated bank accounts.
 * It also includes functionalities like password validation.
 */
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<Account> accounts;
    private String CurrencyType;
    private String email;



    /**
     * Constructs a User with specified personal and financial details.
     * Initializes the user with given ID, names, username, password, currency type, and email.
     * Initializes an empty list of accounts.
     *
     * @param userId    The unique identifier for the user.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param username  The username for the user.
     * @param password  The password for the user.
     * @param CurType   The currency type preferred by the user.
     * @param email     The email address of the user.
     */
    public User(int userId, String firstName, String lastName, String username, String password,
                String CurType, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.CurrencyType = CurType;
        this.email = email;
    }


    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

}