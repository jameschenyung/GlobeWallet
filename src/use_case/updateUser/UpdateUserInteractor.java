package use_case.updateUser;

import use_case.signup.SignupOutputData;

/**
 * The {@code UpdateUserInteractor} class handles the business logic for updating user information.
 * It implements the {@code UpdateUserInputBoundary} interface and uses a data access interface
 * to interact with the data layer and an output boundary to communicate the result of the operation.
 */
public class UpdateUserInteractor implements UpdateUserInputBoundary{
    private final UpdateUserDataAccessInterface userDataAccess;
    private UpdateUserOutputBoundary UpdateUserOutputBoundary;

    /**
     * Constructs an {@code UpdateUserInteractor} with the specified data access and output boundary interfaces.
     *
     * @param userDataAccess           The data access interface to interact with user data.
     * @param UpdateUserOutputBoundary The output boundary interface to communicate the results of the update operation.
     */
    public UpdateUserInteractor(UpdateUserDataAccessInterface userDataAccess, UpdateUserOutputBoundary UpdateUserOutputBoundary) {
        this.userDataAccess = userDataAccess;
        this.UpdateUserOutputBoundary = UpdateUserOutputBoundary;
    }

    /**
     * Executes the update operation with the provided user input data.
     * This method validates the provided information, updates the user data if valid,
     * and communicates the outcome through the output boundary.
     *
     * @param updateUserInputData The input data for the update operation, containing new user information.
     * @throws Exception If an error occurs during the update process.
     */
    @Override
    public void execute(UpdateUserInputData updateUserInputData) throws Exception {
        if (!(updateUserInputData.getRepeatNewPassword().equals(updateUserInputData.getNewPassword()))){
            UpdateUserOutputBoundary.prepareFailView("Passwords don't match");
        }
        else if (userDataAccess.isUsernameTaken(updateUserInputData.getNewUsername())) {
            // Handle the case where username is already taken
            UpdateUserOutputBoundary.prepareFailView("Username already exists.");
        }
        else if (!(userDataAccess.validatePassword(updateUserInputData.getNewPassword()))) {
            // Handle the case where the password does not meet the policy
            UpdateUserOutputBoundary.prepareFailView("Invalid password.");
        }
        else {
            userDataAccess.updateUser(
                    userDataAccess.getCurrentUserId(),
                    updateUserInputData.getNewEmail(),
                    updateUserInputData.getNewUsername(),
                    updateUserInputData.getNewPassword()
            );

            UpdateUserOutputBoundary.prepareSuccessView(new UpdateUserOutputData(
                    true,
                    "Update successful!",
                    userDataAccess.getUserByUsername(updateUserInputData.getNewUsername()).getUserId())
            );
        }
    }
}
