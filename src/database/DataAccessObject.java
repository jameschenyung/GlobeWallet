package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import objects.User;
import objects.Account;

public class DataAccessObject implements use_case.login.LoginUserDataAccessInterface,
        use_case.signup.SignupUserDataAccessInterface{
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Save account data
    public void saveAccount(String accountId, int userId, double balance) throws SQLException {
        String sql = "INSERT INTO accounts (accountId, userId, balance) VALUES (?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountId);
            pstmt.setInt(2, userId);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();
        }
    }

    // Update user data
    public void updateUser(int id, String firstName, String lastName, String username, String password) throws SQLException {
        String sql = "UPDATE users SET firstName = ?, lastName = ?, username = ?, password = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        }
    }

    // Update account balance
    public void updateAccountBalance(String accountId, double newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountId);
            pstmt.executeUpdate();
        }
    }

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

    // Get user data
    public User getUser(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        }
        return null; // Or throw an exception
    }

    // Get account data
    public Account getAccount(String accountId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getString("accountId"),
                        getUser(rs.getInt("userId"))
                );
            }
        }
        return null; // Or throw an exception
    }

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
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean validatePassword(String username, String password) {
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

    @Override
    public User createUser(String firstName, String lastName, String username, String password) {
        String sqlInsertUser = "INSERT INTO users (id, firstName, lastName, username, password) VALUES (?, ?, ?, ?, ?)";
        String sqlCheckId = "SELECT COUNT(id) FROM users WHERE id = ?";
        int randomId;

        try (Connection conn = this.connect();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheckId);
             PreparedStatement insertStmt = conn.prepareStatement(sqlInsertUser)) {

            do {
                randomId = (int) ((Math.random() * (99999999 - 10000000)) + 10000000); // Generate 8-digit ID
                checkStmt.setInt(1, randomId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        break; // ID is not taken, exit loop
                    }
                }
            } while (true); // Keep looping until an unused ID is found

            insertStmt.setInt(1, randomId);
            insertStmt.setString(2, firstName);
            insertStmt.setString(3, lastName);
            insertStmt.setString(4, username);
            insertStmt.setString(5, password);

            int affectedRows = insertStmt.executeUpdate();

            // Check the affected rows
            if (affectedRows > 0) {
                return new User(randomId, firstName, lastName, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    @Override
    public boolean validatePassword(String password) {
        // Regex to check for a password that has at least one uppercase letter,
        // at least one lowercase letter, at least one digit, no symbols,
        // and is at least 8 characters long.
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        // Check if the password matches the regex
        return password != null && password.matches(passwordRegex);
    }
}
