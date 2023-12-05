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
public class loginDataAccessObject implements use_case.login.LoginUserDataAccessInterface{

    private static final String DB_URL = "jdbc:sqlite:bank.db";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
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
     * Checks if the provided password matches the stored password for a given username.
     *
     * @param username the username of the user
     * @param password the password to check
     * @return {@code true} if the password matches, {@code false} otherwise.
     */
    @Override
    public boolean checkPassword(String username, String password) {
        // This method should validate the password, possibly using hashing and salt
        // In this example, we're just checking plaintext passwords which is insecure
        String sql = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password); // In real-world, compare hashed passwords
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Sets the current user in the database.
     *
     * @param username the username of the current user
     * @param userid the user ID of the current user
     */
    @Override
    public void setCurrentUser(String username, Integer userid) {
        String sql = "INSERT OR REPLACE INTO current_user (id, userId, username) VALUES (1, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userid);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
