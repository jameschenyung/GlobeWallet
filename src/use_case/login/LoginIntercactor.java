package use_case.login;

public class LoginInteractor implements LoginInputBoundary {
    final LoginOutputBoundary outputBoundary;
    final LoginUserDataAccessInterface userDataAccess;

    public LoginInteractor(LoginOutputBoundary outputBoundary, LoginUserDataAccessInterface userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void requestLogin(LoginInputData inputData) {
        User user = userDataAccess.getUserByAccountID(inputData.getAccountID());
        if (user != null && user.getPassword().equals(inputData.getPassword())) {
            outputBoundary.presentLoginResult(new LoginOutputData(true, "Login successful"));
        } else {
            outputBoundary.presentLoginResult(new LoginOutputData(false, "Invalid credentials"));
        }
    }
}

