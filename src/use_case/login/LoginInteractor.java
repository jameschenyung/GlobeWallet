package use_case.login;

import objects.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginOutputBoundary outputBoundary;
    final LoginUserDataAccessInterface userDataAccess;

    public LoginInteractor(LoginOutputBoundary outputBoundary, LoginUserDataAccessInterface userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void requestLogin(LoginInputData inputData) {
        User user = userDataAccess.getUserByUsername(inputData.getUsername());
        if (user != null && user.getPassword().equals(inputData.getPassword())) {
            outputBoundary.presentLoginResult(new LoginOutputData(true, "Login successful"));
        } else {
            outputBoundary.presentLoginResult(new LoginOutputData(false, "Invalid credentials"));
        }
    }
}
