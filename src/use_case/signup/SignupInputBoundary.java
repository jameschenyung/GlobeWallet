package use_case.signup;

/**
 * Interface defining the contract for an input boundary in the signup use case.
 * This interface should be implemented by any class (typically an interactor) that handles
 * the logic for processing user signup requests. It defines a method for executing the signup
 * operation based on the provided input data.
 */
public interface SignupInputBoundary {
    /**
     * Executes the signup process using the given input data.
     * Implementing classes should handle the logic of registering a new user based on the information
     * provided in the signupInputData object.
     *
     * @param signupInputData The input data containing the necessary information for user registration.
     */
    void execute(SignUpInputData signupInputData);
}
