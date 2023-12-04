package objects;

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

    /**
     * Checks if the provided password meets specific criteria.
     * The password must contain at least one uppercase letter, one lowercase letter, one number,
     * and be at least 8 characters long.
     *
     * @param pass The password to be checked.
     * @return True if the password meets the criteria, false otherwise.
     */
    public Boolean passwordChecker(String pass) {
        boolean upper = false;
        boolean lower = false;
        boolean number = false;

        for (int i = 0; i < pass.length(); i++) {
            char element = pass.charAt(i);
            if (Character.isUpperCase(element)) {
                upper = true;
            }
            else if (Character.isLowerCase(element)) {
                lower = true;
            }
            else if (Character.isDigit(element)) {
                number = true;
            }

            if (upper && lower && number) {
                break;
            }
        }
        return (pass.length() >= 8) && upper && lower && number;
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

    /**
     * Retrieves the user's password.
     * Note: Exposing a password through a getter can be a security risk.
     *
     * @return The password of the user.
     */
    public Object getPassword() {return getPassword();}
}