package use_case.signup;

/**
 * Interface defining the contract for an output boundary in the signup use case.
 * This interface should be implemented by any class (typically a presenter) that handles
 * the presentation logic for displaying the results of a user signup attempt. It defines methods
 * for preparing the view in case of a successful signup or a failure.
 */
public interface SignupOutputBoundary {
    /**
     * Prepares and presents the view for a successful user signup.
     * Implementing classes should handle the logic to display or communicate the success of the signup process,
     * potentially including information about the newly registered user.
     *
     * @param user The data containing information about the successful signup, encapsulated in a SignupOutputData object.
     */
    void prepareSuccessView(SignupOutputData user);

    /**
     * Prepares and presents the view for a failed signup attempt.
     * Implementing classes should handle the logic to display or communicate the failure of the signup process,
     * including the error message explaining the reason for failure.
     *
     * @param error The error message describing why the signup attempt failed.
     */
    void prepareFailView(String error);
}
