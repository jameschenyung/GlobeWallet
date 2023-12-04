package use_case.updateUser;

import objects.User;

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

    void updateUser(int id, String email, String username, String password);

    User getUserByUsername(String username);
}
