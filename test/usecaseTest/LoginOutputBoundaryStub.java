package usecaseTest;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginOutputBoundaryStub implements LoginOutputBoundary {
    private LoginOutputData outputData;

    @Override
    public void present(LoginOutputData outputData) {
        this.outputData = outputData;
    }

    public LoginOutputData getOutputData() {
        return outputData;
    }

    @Override
    public void prepareSuccessView(LoginOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
