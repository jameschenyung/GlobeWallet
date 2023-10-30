package objects;

import java.util.*;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<Account> accounts;

    public User(int userId, String firstName, String lastName, String username, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
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