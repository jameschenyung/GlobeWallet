package use_case.updateUser;

import interface_adapter.EmailSender.EmailSenderGateway;
import use_case.signup.SignupOutputData;

public class UpdateUserInteractor implements UpdateUserInputBoundary{
    private final UpdateUserDataAccessInterface userDataAccess;
    private UpdateUserOutputBoundary UpdateUserOutputBoundary;
    private EmailSenderGateway emailSenderGateway;

    public UpdateUserInteractor(UpdateUserDataAccessInterface userDataAccess, UpdateUserOutputBoundary UpdateUserOutputBoundary) {
        this.userDataAccess = userDataAccess;
        this.UpdateUserOutputBoundary = UpdateUserOutputBoundary;
    }
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

            String email = updateUserInputData.getNewEmail();
            String username = updateUserInputData.getNewUsername();
            Integer userid = userDataAccess.getUserIdFromUserName(username);
            String fullName = userDataAccess.getFullName(userid);

            emailSenderGateway.sendUpdateConfirmation(email, fullName);

            UpdateUserOutputBoundary.prepareSuccessView(new UpdateUserOutputData(
                    true,
                    "Update successful!",
                    userDataAccess.getUserByUsername(updateUserInputData.getNewUsername()).getUserId())
            );
        }
    }
}
