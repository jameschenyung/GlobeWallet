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
    /**
     * The {@code DataAccessObject} class provides methods for interacting with a banking database.
     * It implements various interfaces to support operations related to user login, sign-up, money transfers,
     * account management, and transaction creation.
     * This class uses SQLite for database interactions.
     */
public class DataAccessObject implements use_case.login.LoginUserDataAccessInterface,
        use_case.signup.SignupUserDataAccessInterface,
        use_case.sendmoneytransfer.SendMoneyUserDataAccessInterface,
        use_case.addAccount.AccountDataAccessInterface,

        use_case.receiveMoney.receiveMoneyDataAccessInterface{

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
        // Generate a random double value within the range 1 to 1000000
        return 1 + (999999 * random.nextDouble());
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
        String sql = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        }
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
     * Retrieves user information based on the user ID.
     *
     * @param userId the unique identifier of the user
     * @return a {@code User} object containing the user's information, or {@code null} if the user is not found.
     * @throws SQLException if a database access error occurs
     */
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

    /**
     * Retrieves account information based on the account ID.
     *
     * @param accountId the account ID as a string
     * @return an {@code Account} object containing the account's information, or {@code null} if the account is not found.
     * @throws SQLException if a database access error occurs
     */
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
         * Checks if a transaction exists and is not yet received.
         *
         * @param transactionId The ID of the transaction to check.
         * @return {@code true} if the transaction exists and is not received, {@code false} otherwise.
         * @throws SQLException If there is a database access error or the method is called on a closed connection.
         */
        @Override
        public boolean hasTransaction(Integer transactionId) {
            String sql = "SELECT COUNT(1) FROM transactions WHERE transactionId = ? AND received = 0";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, transactionId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt(1) > 0;
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
}
