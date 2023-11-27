package presenter;

import use_case.login.*;
import views.MainFrame;
import views.SignInPanel;

import javax.swing.*;

public class LoginPresenter implements LoginOutputBoundary {
    private SignInPanel view;
    private MainFrame frame;
    private LoginInteractor interactor;

    public LoginPresenter(SignInPanel view, MainFrame frame, LoginUserDataAccessInterface userDataAccess){
        this.view = view;
        this.frame = frame;
        this.interactor = new LoginInteractor(this, userDataAccess);
        this.view.setPresenter(this);
    }

    public void setInteractor(LoginInteractor interactor) {this.interactor = interactor; }
    public void handleLogin(String userName, String password) {
        LoginInputData inputData = new LoginInputData(userName, password);
        interactor.execute(inputData);
    }


    @Override
    public void prepareSuccessView(LoginOutputData data) {
        SwingUtilities.invokeLater(() -> view.showSuccess(data.getMessage()));
        Timer timer = new Timer(2000, e -> {
            frame.switchToHomePanel();
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void prepareFailView(String error) {
        view.showError(error);
    }
}
