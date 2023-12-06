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
public class signupDataAccessObject implements use_case.signup.SignupUserDataAccessInterface{
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private int generateUniqueId() {
        Random rand = new Random();
        int randomId;
        String sqlCheckId = "SELECT COUNT(id) FROM users WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheckId)) {

            while (true) {
                randomId = 10000000 + rand.nextInt(90000000); // Generate 8-digit ID
                checkStmt.setInt(1, randomId);

                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        return randomId; // ID is not taken, return this ID
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Error checking for unique ID", e);
        }
    }

    /**
     * Creates a new user in the database.
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param currencyType the currency type preferred by the user
     * @param email the email address of the user
     * @return a new {@code User} object
     * @throws RuntimeException if a database operation fails
     */
    public User createUser(String firstName, String lastName, String username, String password, String currencyType, String email) {
        String sqlInsertUser = "INSERT INTO users (id, firstName, lastName, username, password, currencyType, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement insertStmt = conn.prepareStatement(sqlInsertUser)) {

            int randomId = generateUniqueId(); // Separate method to generate unique ID

            insertStmt.setInt(1, randomId);
            insertStmt.setString(2, firstName);
            insertStmt.setString(3, lastName);
            insertStmt.setString(4, username);
            insertStmt.setString(5, password);
            insertStmt.setString(6, currencyType);
            insertStmt.setString(7, email);

            int affectedRows = insertStmt.executeUpdate();

            if (affectedRows > 0) {
                return new User(randomId, firstName, lastName, username, password, currencyType, email);
            } else {
                throw new SQLException("Creating user failed, no rows affected.");
            }

        } catch (SQLException e) {
            // Consider a custom exception class if you want to handle database exceptions in a specific way
            throw new RuntimeException("Database operation failed", e);
        }
    }

    /**
     * Checks if the specified username is already taken in the database.
     *
     * @param username the username to check
     * @return {@code true} if the username is already taken, {@code false} otherwise.
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean isUsernameTaken(String username) {
        String sql = "SELECT COUNT(id) FROM users WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Validates a password based on specified criteria.
     * The password must contain at least one uppercase letter, one lowercase letter, one digit,
     * no symbols, and be at least 8 characters long.
     *
     * @param password the password to validate
     * @return {@code true} if the password meets the criteria, {@code false} otherwise.
     */
    @Override
    public boolean validatePassword(String password) {
        // Regex to check for a password that has at least one uppercase letter,
        // at least one lowercase letter, at least one digit, no symbols,
        // and is at least 8 characters long.
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        // Check if the password matches the regex
        return password != null && password.matches(passwordRegex);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username the username of the user
     * @return a {@code User} object if a user with the given username exists, otherwise {@code null}.
     */
    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Assuming User class has a constructor that matches this data
                return new User(rs.getInt("id"), rs.getString("firstName"),
                        rs.getString("lastName"), rs.getString("username"),
                        rs.getString("password"), rs.getString("CurrencyType"),
                        rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
