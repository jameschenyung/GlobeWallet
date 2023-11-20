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
        if (inputData == null || inputData.getUsername() == null || inputData.getPassword() == null) {
            outputBoundary.presentLoginResult(new LoginOutputData(false, "Invalid input data"));
            return;
        }

        try {
            User user = userDataAccess.getUserByUsername(inputData.getUsername());
            if (user != null && checkPassword(user, inputData.getPassword())) {
                outputBoundary.presentLoginResult(new LoginOutputData(true, "Login successful"));
            } else {
                outputBoundary.presentLoginResult(new LoginOutputData(false, "Invalid credentials"));
            }
        } catch (Exception e) {
            outputBoundary.presentLoginResult(new LoginOutputData(false, "Login failed due to system error"));
        }
    }

    private boolean checkPassword(User user, String inputPassword) {
        return user.getPassword().equals(inputPassword);
    }
}
