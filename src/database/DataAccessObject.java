package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import objects.User;
import objects.Account;

import java.util.Currency;
import java.util.Random;
import java.util.Set;

public class DataAccessObject implements use_case.login.LoginUserDataAccessInterface,
        use_case.signup.SignupUserDataAccessInterface,
        use_case.sendmoneytransfer.SendMoneyUserDataAccessInterface,
        use_case.addAccount.AccountDataAccessInterface,
        use_case.receiveMoney.receiveMoneyDataAccessInterface
{
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Save account data
    public void saveAccount(Integer accountId, int userId, double balance) throws SQLException {
        String sql = "INSERT INTO accounts (accountId, userId, balance) VALUES (?, ?, ?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            pstmt.setInt(2, userId);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();
        }
    }

    @Override
    public double generateBalance() {
        Random random = new Random();
        return random.nextDouble() * Double.MAX_VALUE;
    }

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

    @Override
    public boolean isValidAccount(Integer accountId) {
        String sql = "SELECT COUNT(1) FROM accounts WHERE accountId = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
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
                        rs.getString("password"),
                        rs.getString("CurrencyType"),
                        rs.getString("email")
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
                        rs.getInt("accountId"),
                        rs.getInt("userId"),
                        rs.getDouble("balance"),
                        rs.getString("CurrencyType")
                );
            }
        }
        return null; // Or throw an exception
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
