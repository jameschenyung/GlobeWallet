package use_case.login;

public class LoginInteractor implements LoginInputBoundary {
    final LoginOutputBoundary outputBoundary;
    final LoginUserDataAccessInterface userDataAccess;

    public LoginInteractor(LoginOutputBoundary outputBoundary, LoginUserDataAccessInterface userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void execute(LoginInputData inputData) {
        if (inputData == null || inputData.getUsername() == null || inputData.getPassword() == null) {
            outputBoundary.prepareFailView("Invalid input.");
        }
        else if (userDataAccess.getUserByUsername(inputData.getUsername()) == null ||
                !userDataAccess.checkPassword(inputData.getUsername(), inputData.getPassword())) {
            outputBoundary.prepareFailView("Invalid username or password.");
        }
        else {
            userDataAccess.setCurrentUser(inputData.getUsername(),
                    userDataAccess.getUserByUsername(inputData.getUsername()).getUserId());
            outputBoundary.prepareSuccessView(new LoginOutputData(true, "Log in successful."));
        }
    }
}
