package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import Entity.Transaction;
import Entity.User;
import Entity.Account;

import java.util.Currency;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.math.BigDecimal;

public class sendMoneyDataAccessObject implements use_case.sendmoneytransfer.SendMoneyUserDataAccessInterface{
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param accountId the unique identifier of the account
     * @return an {@code Account} object corresponding to the given ID, or {@code null} if no account is found.
     */
    @Override
    public Account getAccountById(Integer accountId) {
        String sql = "SELECT * FROM accounts WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Assuming the Account class has a constructor that takes these fields
                return new Account(
                        rs.getInt("accountId"),
                        rs.getInt("userId"),
                        rs.getDouble("balance"),
                        rs.getString("currencyType")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Or handle accordingly
    }

    /**
     * Updates the balance of a specific account.
     *
     * @param accountId the unique identifier of the account
     * @param newBalance the new balance to be set
     * @throws SQLException if a database access error occurs
     */
    // Update account balance
    public void updateAccountBalance(Integer accountId, double newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, accountId);
            pstmt.executeUpdate();
        }
    }

    /**
     * Validates if a given currency is valid and available.
     *
     * @param currency the currency code to validate
     * @return {@code true} if the currency is valid, {@code false} otherwise.
     */

    // validate currency
    @Override
    public boolean isValidCurrency(String currency) {
        Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
        for (Currency currencies : availableCurrencies) {
            if (currencies.getCurrencyCode().equalsIgnoreCase(currency)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an account with a given ID exists.
     *
     * @param accountId the unique identifier of the account
     * @return {@code true} if the account exists, {@code false} otherwise.
     */
    @Override
    public boolean isValidAccount(Integer accountId) {
        if (accountId == null) {
            return false; // Account ID should not be null.

        }

        String sql = "SELECT EXISTS (SELECT 1 FROM accounts WHERE accountId = ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return !rs.getBoolean(1); // Returns true if the account exists, false otherwise
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Depending on your application's error handling strategy,
            // you might want to log this error or rethrow a custom exception.
        }
        return false;
    }

    /**
     * Retrieves the currency type of a specified account.
     *
     * @param accountId the unique identifier of the account
     * @return the currency type of the account as a string, or {@code null} if the account is not found.
     */
    @Override
    public String getCurrencyByAccount(Integer accountId) {
        String sql = "SELECT currencyType FROM accounts WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("currencyType");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the balance of a specified account.
     *
     * @param accountId the unique identifier of the account
     * @return the balance of the account, or {@code null} if the account is not found.
     */
    @Override
    public Double getAccountBalance(Integer accountId) {
        String sql = "SELECT balance FROM accounts WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a new transaction record in the database.
     *
     * @param transactionId the unique identifier of the transaction
     * @param SendId the unique identifier of the sender
     * @param ReceiverId the unique identifier of the receiver
     * @param amount the transaction amount
     * @param SecurityCode the security code of the transaction
     * @param received a flag indicating whether the transaction was received
     * @throws RuntimeException if a database access error occurs
     */
    @Override
    public void createTransaction(Integer transactionId, Integer SendId, Integer ReceiverId, Double amount, Integer SecurityCode,
                                  Integer received) {
        String sql = "INSERT INTO transactions (transactionId, senderId, receiverId, amount, securityCode, received) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, transactionId);
            pstmt.setInt(2, SendId);
            pstmt.setInt(3, ReceiverId);
            pstmt.setDouble(4, amount);
            pstmt.setInt(5, SecurityCode);
            pstmt.setInt(6, received);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a unique transaction ID.
     *
     * @return a unique transaction ID
     * @throws IllegalStateException if an error occurs during the ID generation process
     */
    public int generateUniqueTransactionId() {
        Random rand = new Random();
        int randomId;
        String sqlCheckId = "SELECT COUNT(transactionId) FROM transactions WHERE transactionId = ?";

        try (Connection conn = this.connect();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheckId)) {

            while (true) {
                randomId = rand.nextInt(Integer.MAX_VALUE); // Generate a random ID
                checkStmt.setInt(1, randomId);

                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        return randomId; // ID is not taken, return this ID
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error checking for unique transaction ID", e);
        }
    }

    /**
     * Retrieves the email address of a user based on the user ID.
     *
     * @param userId the unique identifier of the user
     * @return the email address of the user, or {@code null} if the user is not found.
     */
    public String getEmail(int userId) {
        String sql = "SELECT email FROM users WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Or throw an exception
    }

    /**
     * Retrieves the full name of a user given their user ID.
     * The method queries the database to find the user's first and last name.
     *
     * @param userId The user ID for which the full name is to be retrieved.
     * @return The full name of the user (firstName + " " + lastName) or {@code null} if the user is not found or in case of an SQL exception.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    public String getFullName(Integer userId) {
        String sql = "SELECT firstName, lastName FROM users WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                return firstName + " " + lastName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if user not found or if there's an error
    }

    /**
     * Retrieves the user ID associated with a given account ID.
     *
     * @param accountId The account ID for which the user ID is to be retrieved.
     * @return The user ID associated with the account or {@code null} if the account is not found or in case of an SQL exception.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    @Override
    public Integer getUserIdbyAccountId(Integer accountId) {
        String sql = "SELECT userId FROM accounts WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("userId");
            } else {
                return null; // Account not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Error occurred
        }
    }

}
