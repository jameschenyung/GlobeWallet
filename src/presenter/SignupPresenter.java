package presenter;

import use_case.signup.*;
import views.SignupView;

import javax.swing.*;

public class SignupPresenter implements SignupOutputBoundary {
    private final SignupView view;
    private SignupInteractor interactor;

    public SignupPresenter(SignupView view, SignupUserDataAccessInterface userDataAccess) {
        this.view = view;
        this.interactor = new SignupInteractor(userDataAccess, this);
        this.view.setPresenter(this);
    }

    public void setInteractor(SignupInteractor interactor) {
        this.interactor = interactor;
    }

    public void handleSignup(String firstName, String lastName, String username, String email, String repeatEmail, String password, String repeatPassword) {
        SignUpInputData inputData = new SignUpInputData(firstName, lastName, username, email, repeatEmail, password, repeatPassword);
        interactor.execute(inputData);
    }

    @Override
    public void prepareSuccessView(SignupOutputData data) {
        SwingUtilities.invokeLater(() -> view.showSuccess(data.getMessage()));
    }

    @Override
    public void prepareFailView(String error) {
        SwingUtilities.invokeLater(() -> view.showError(error));
    }
}
