package use_case.signup;
import objects.User;
public interface SignupUserDataAccessInterface {
    /**
     * Creates a new user with the given details.
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username for the user
     * @param password the password for the user
     * @return the User object if creation is successful, null otherwise
     */
    User createUser(String firstName, String lastName, String username, String password);

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
}
