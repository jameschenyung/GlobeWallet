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
/**
 * The {@code DataAccessObject} class provides methods for interacting with a banking database.
 * It implements various interfaces to support operations related to user login, sign-up, money transfers,
 * account management, and transaction creation.
 * This class uses SQLite for database interactions.
 */
public class receiveMoneyDataAccessObject implements
        use_case.receiveMoney.receiveMoneyDataAccessInterface {

    private static final String DB_URL = "jdbc:sqlite:bank.db";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    /**
     Retrieves the current user's ID from the database.*
     @return the user ID of the currently logged-in user, or {@code null} if no user is logged in.*/
    public Integer getCurrentUserId() {
        Integer userId = null;
        String query = "SELECT userId FROM current_user WHERE id = 1";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("userId");}} catch (Exception e) {
            e.printStackTrace();}
        return userId;}

    @Override
    public boolean accountUnderCurrentUser(Integer accountId) {
        Integer currentUserId = getCurrentUserId();
        if (currentUserId == null) {
            return false; // No user is currently logged in.
        }

        String sql = "SELECT userId FROM accounts WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Integer accountUserId = rs.getInt("userId");
                return accountUserId.equals(currentUserId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Account not found or other error.
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

    @Override
    public boolean validateSecurityCode(Integer securityCode, Integer transactionId) {
        String sql = "SELECT securityCode FROM transactions WHERE transactionId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int storedSecurityCode = rs.getInt("securityCode");
                return storedSecurityCode == securityCode;
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    @Override
    public Transaction getTransactionDetails(Integer transactionId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE transactionId = ?";
        Transaction transaction = null;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                transaction = new Transaction(
                        rs.getInt("transactionId"),
                        rs.getInt("senderId"),
                        rs.getInt("receiverId"),
                        rs.getDouble("amount"),
                        rs.getInt("securityCode"),
                        rs.getInt("received")
                );
            }
        }
        return transaction;
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
     * Checks if a transaction exists and is not yet received.
     *
     * @param transactionId The ID of the transaction to check.
     * @return {@code true} if the transaction exists and is not received, {@code false} otherwise.
     * @throws SQLException If there is a database access error or the method is called on a closed connection.
     */
    @Override
    public boolean hasTransaction(Integer transactionId) {
        String sql = "SELECT COUNT(1) FROM transactions WHERE transactionId = ? AND received = 1";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Transaction not found or error occurred
    }

    /**
     * Retrieves the sender ID of a specified transaction.
     *
     * @param transactionId The transaction ID for which the sender ID is to be retrieved.
     * @return The sender ID of the transaction or {@code null} if the transaction is not found or in case of an SQL exception.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    @Override
    public Integer getTransactionSenderId(Integer transactionId) {
        String sql = "SELECT senderId FROM transactions WHERE transactionId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("senderId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the receiver ID of a specified transaction.
     *
     * @param transactionId The transaction ID for which the receiver ID is to be retrieved.
     * @return The receiver ID of the transaction or {@code null} if the transaction is not found or in case of an SQL exception.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    @Override
    public Integer getTransactionReceiverId(Integer transactionId) {
        String sql = "SELECT receiverId FROM transactions WHERE transactionId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("receiverId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the receiver ID of a specified transaction.
     *
     * @param transactionId The transaction ID for which the receiver ID is to be retrieved.
     * @return The receiver ID of the transaction or {@code null} if the transaction is not found or in case of an SQL exception.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */
    @Override
    public double getTransactionAmount(Integer transactionId) {
        String sql = "SELECT amount FROM transactions WHERE transactionId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("amount");
            } else {
                // Handle the case where the transaction is not found
                throw new SQLException("Transaction with ID " + transactionId + " not found.");
            }
        } catch (SQLException e) {
            // You might want to handle this differently based on your error handling strategy
            e.printStackTrace();
            return 0; // or you could re-throw the exception
        }
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

    /**
     * Marks a transaction as received in the database.
     *
     * @param transactionId The ID of the transaction to be marked as received.
     * @throws SQLException If there is a database access error, this method is called on a closed connection, or an update fails.
     */
    @Override
    public void transactionReceived(Integer transactionId) {
        String sql = "UPDATE transactions SET received = 1 WHERE transactionId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, transactionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Depending on your error handling strategy, you might want to re-throw the exception or handle it differently
        }
    }
}
