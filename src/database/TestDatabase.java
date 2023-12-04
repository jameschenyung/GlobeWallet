package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDatabase {
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    /**
     * Main method to perform a series of database operations for testing purposes.
     * This method sequentially adds a user and an account, then displays all users and accounts.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        addUser("John", "Doe", "johndoe", "password123");
        addAccount("johndoe", "ACC001", 500.0);
        showUsers();
        showAccounts();
    }

    private static void addUser(String firstName, String lastName, String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (firstName, lastName, username, password) VALUES (?, ?, ?, ?)")) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addAccount(String username, String accountId, double balance) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO accounts (accountId, userId, balance) VALUES (?, (SELECT id FROM users WHERE username = ?), ?)")) {

            pstmt.setString(1, accountId);
            pstmt.setString(2, username);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showUsers() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users")) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showAccounts() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM accounts")) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("accountId") + " - " + rs.getDouble("balance"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
