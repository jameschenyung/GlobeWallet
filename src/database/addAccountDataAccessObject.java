package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import objects.Transaction;
import objects.User;
import objects.Account;

import java.util.Currency;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.math.BigDecimal;

public class addAccountDataAccessObject implements use_case.addAccount.AccountDataAccessInterface{
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * Saves account data into the database.
     *
     * @param accountId the unique identifier of the account
     * @param userId the unique identifier of the user associated with the account
     * @param balance the current balance of the account
     * @throws SQLException if a database access error occurs or this method is called on a closed connection
     */
    // Save account data
    public void saveAccount(Integer accountId, Integer userId, double balance, String currencyType) throws SQLException {
        String sql = "INSERT INTO accounts (accountId, userId, balance, currencyType) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.setInt(2, userId);
            pstmt.setDouble(3, balance);
            pstmt.setString(4, currencyType);
            pstmt.executeUpdate();
        }
    }

    /**
     * Generates a random balance value range from 1-1000000.
     *
     * @return a randomly generated double value representing a balance.
     */
    @Override
    public double generateBalance() {
        Random random = new Random();
        double rangeMin = 1.00;
        double rangeMax = 1000000.00;

        // Generate a random double within the range
        double randomBalance = rangeMin + (rangeMax - rangeMin) * random.nextDouble();

        // Use BigDecimal for precise rounding to two decimal places
        BigDecimal roundedBalance = new BigDecimal(randomBalance).setScale(2, BigDecimal.ROUND_HALF_UP);
        return roundedBalance.doubleValue();
    }

    /**
     * Retrieves the current user's ID from the database.
     *
     * @return the user ID of the currently logged-in user, or {@code null} if no user is logged in.
     */
    @Override
    public Integer getCurrentUserId() {
        Integer userId = null;
        String query = "SELECT userId FROM current_user WHERE id = 1";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("userId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
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
     * return the list of all the bank accounts that the user has
     * @param currentUserId account id inputted
     * @return list
     */
    public ArrayList<Integer> getUserBankAccounts(Integer currentUserId) {
        ArrayList<Integer> userAccounts = new ArrayList<>();
        String sql = "SELECT accountId FROM accounts WHERE userId = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, currentUserId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int accountId = rs.getInt("accountId");
                userAccounts.add(accountId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAccounts;
    }

    /**
     * Retrieves account information based on the account ID.
     *
     * @param accountId the account ID as a string
     * @return an {@code Account} object containing the account's information, or {@code null} if the account is not found.
     * @throws SQLException if a database access error occurs
     */
    // Get account data
    public Account getAccount(String accountId) {
        String sql = "SELECT * FROM accounts WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("accountId"),
                        rs.getInt("userId"),
                        rs.getDouble("balance"),
                        rs.getString("CurrencyType")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Or throw an exception
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
}
