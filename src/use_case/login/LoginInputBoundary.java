package use_case.login;

/**
 * Interface defining the contract for an input boundary in the login use case.
 * This interface should be implemented by any class (typically an interactor) that handles
 * the logic for processing user login requests. It defines a method for executing the login
 * operation based on the provided input data.
 */
public interface LoginInputBoundary {
        /**
         * Executes the login process using the given input data.
         * Implementers should handle the logic of authenticating a user based on the credentials
         * provided in the inputData object.
         *
         * @param inputData The input data containing the credentials for login (e.g., username and password).
         */
        void execute(LoginInputData inputData);
}
