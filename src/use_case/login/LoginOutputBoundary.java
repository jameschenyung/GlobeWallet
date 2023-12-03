package use_case.login;

/**
 * Interface defining the contract for an output boundary in the login use case.
 * This interface should be implemented by any class (typically a presenter) that handles
 * the presentation logic for displaying the results of a user login attempt. It defines methods
 * for preparing the view in case of a successful login or a failure.
 */

public interface LoginOutputBoundary {

    /**
     * Prepares and presents the view for a successful login attempt.
     * Implementing classes should handle the logic to display or communicate the success of the login process,
     * potentially including information about the logged-in user.
     *
     * @param user The data containing information about the successful login, encapsulated in a LoginOutputData object.
     */
    void prepareSuccessView(LoginOutputData user);

    /**
     * Prepares and presents the view for a failed login attempt.
     * Implementing classes should handle the logic to display or communicate the failure of the login process,
     * including the error message explaining the reason for failure.
     *
     * @param error The error message describing why the login attempt failed.
     */
    void prepareFailView(String error);
}

