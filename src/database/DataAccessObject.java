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
public class DataAccessObject{

    private static final String DB_URL = "jdbc:sqlite:bank.db";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
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
         * return userId from username
         * @param username username of the user
         * @return userId
         */
        public Integer getUserIdFromUserName(String username) throws SQLException {
            String sql = "SELECT id FROM users WHERE username = ?";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
            return null;
        }
    }
