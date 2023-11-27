package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InitDatabase {
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Create users table with an additional column for CurrencyType
            String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "firstName TEXT NOT NULL," +
                    "lastName TEXT NOT NULL," +
                    "username TEXT NOT NULL UNIQUE," +
                    "password TEXT NOT NULL," +
                    "currencyType TEXT NOT NULL," + // Added currencyType column
                    "email TEXT NOT NULL" + // added email column
                    ")";
            stmt.execute(createUserTable);

            // Create accounts table with an additional column for CurrencyType
            String createAccountTable = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "accountId TEXT PRIMARY KEY," +
                    "userId INTEGER," +
                    "balance DOUBLE DEFAULT 0," +
                    "currencyType TEXT NOT NULL," + // Added currencyType column
                    "FOREIGN KEY (userId) REFERENCES users(id)" +
                    ")";
            stmt.execute(createAccountTable);

            // Create Transection table
            String createTransactionTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                    "transactionId TEXT PRIMARY KEY," +
                    "senderId TEXT NOT NULL," +
                    "receiverId TEXT NOT NULL," +
                    "amount DOUBLE NOT NULL," +
                    "securityCode INTEGER NOT NULL," +
                    "FOREIGN KEY (senderId) REFERENCES accounts(id)," +
                    "FOREIGN KEY (receiverId) REFERENCES accounts(id)" +
                    ")";
            stmt.execute(createTransactionTable);

            // Create CurrentUser table
            String createCurrentUserTable = "CREATE TABLE IF NOT EXISTS current_user (" +
                    "id INTEGER PRIMARY KEY CHECK (id = 1)," +
                    "userId INTEGER," +
                    "username TEXT NOT NULL," +
                    "FOREIGN KEY (userId) REFERENCES users(id)," +
                    "FOREIGN KEY (username) REFERENCES users(username)" +
                    ")";
            stmt.execute(createCurrentUserTable);


            System.out.println("Database and tables initialized.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
