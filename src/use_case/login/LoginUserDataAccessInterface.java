package use_case.login;

import Entity.User;

public interface LoginUserDataAccessInterface {
    /**
     * Retrieves a user object by username.
     * @param username the username of the user to retrieve
     * @return the User object corresponding to the username, or null if not found
     */
    User getUserByUsername(String username);

    /**
     * Validates the user's password.
     * @param username the username of the user
     * @param password the password to validate
     * @return true if the password is correct, false otherwise
     */
    boolean checkPassword(String username, String password);

    /**
     * set up the current user
     * @param username current username
     * @param userid current userid
     */
    void setCurrentUser(String username, Integer userid);
}
