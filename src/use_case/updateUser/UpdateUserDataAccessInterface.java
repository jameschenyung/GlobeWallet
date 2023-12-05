package use_case.updateUser;

import objects.User;

import java.sql.SQLException;

public interface UpdateUserDataAccessInterface {
    /**
     * Checks if the username is already taken.
     * @param username the username to check
     * @return true if the username is taken, false otherwise
     */
    boolean isUsernameTaken(String username);

    /**
     * Validates the password based on the application's password policy.
     * @param password the password to validate
     * @return true if the password meets the policy, false otherwise
     */
    boolean validatePassword(String password);

    /**
     * update user with new input
     * @param id userid of user being updated
     * @param email new email
     * @param username new username
     * @param password new password
     */
    void updateUser(int id, String email, String username, String password) throws SQLException;

    User getUserByUsername(String username);

    /**
     * Retrieves the current user's ID from the database.
     *
     * @return the user ID of the currently logged-in user, or {@code null} if no user is logged in.
     */
    Integer getCurrentUserId();

    /**
     *
     * @param username
     * @return the User's Id
     */
    Integer getUserIdFromUserName(String username);

    /**
     *
     * @param userid
     * @return the User's Full Name
     */
    String getFullName(Integer userid);
}
