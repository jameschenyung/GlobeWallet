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

public class UpdateUserDataAccessObject implements use_case.updateUser.UpdateUserDataAccessInterface{
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
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
     * Updates a user's information in the database.
     *
     * @param id the unique identifier of the user
     * @param email the user's email
     * @param username the user's username
     * @param password the user's password
     * @throws SQLException if a database access error occurs
     */
    // Update user data
    public void updateUser(int id, String email, String username, String password) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE users SET ");
        ArrayList<Object> params = new ArrayList<>();

        if (email != null && !email.isEmpty()) {
            sql.append("email = ?, ");
            params.add(email);
        }
        if (username != null && !username.isEmpty()) {
            sql.append("username = ?, ");
            params.add(username);
        }
        if (password != null && !password.isEmpty()) {
            sql.append("password = ?, ");
            params.add(password);
        }

        // Remove the last comma and space
        if (params.size() > 0) {
            sql.delete(sql.length() - 2, sql.length());
            sql.append(" WHERE id = ?");
            params.add(id);

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

                for (int i = 0; i < params.size(); i++) {
                    pstmt.setObject(i + 1, params.get(i));
                }

                pstmt.executeUpdate();
            }
        } else {
            // Handle the case where no fields are provided for update
            throw new IllegalArgumentException("No fields provided for update.");
        }
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

    @Override
    public Integer getUserIdFromUserName(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            // Handle SQL exceptions (e.g., log the error)
            e.printStackTrace();
        }
        return null; // User ID not found or there was an error
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


}
