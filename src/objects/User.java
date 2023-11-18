package objects;

import java.util.*;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<Account> accounts;
    private String CurrencyType;



    public User(int userId, String firstName, String lastName, String username, String password, String CurType) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.CurrencyType = CurType;
    }

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
}