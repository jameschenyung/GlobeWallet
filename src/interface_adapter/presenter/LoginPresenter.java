package interface_adapter.presenter;

import use_case.login.*;
import views.MainFrame;
import views.SignInPanel;

import javax.swing.*;

/**
 * The presenter class for the login view.
 * <p>
 * This class handles the interaction between the login view and the login use case (interactor).
 * It implements the {@link LoginOutputBoundary} to respond to the outcome of the login attempt.
 * </p>
 */
public class LoginPresenter implements LoginOutputBoundary {
    private SignInPanel view;
    private MainFrame frame;
    private LoginInteractor interactor;

    /**
     * Constructs a new LoginPresenter.
     *
     * @param view          The sign-in view this presenter is responsible for.
     * @param frame         The main application window.
     * @param userDataAccess The data access interface for user authentication.
     */
    public LoginPresenter(SignInPanel view, MainFrame frame, LoginUserDataAccessInterface userDataAccess){
        this.view = view;
        this.frame = frame;
        this.interactor = new LoginInteractor(this, userDataAccess);
        this.view.setPresenter(this);
    }

    /**
     * Sets the interactor for this presenter.
     *
     * @param interactor The {@link LoginInteractor} to be used by this presenter.
     */
    public void setInteractor(LoginInteractor interactor) {this.interactor = interactor; }

    /**
     * Initiates the login process.
     * <p>
     * This method is called when the user attempts to log in. It creates the necessary input data
     * and triggers the interactor to execute the login process.
     * </p>
     *
     * @param userName The username entered by the user.
     * @param password The password entered by the user.
     */
    public void handleLogin(String userName, String password) {
        LoginInputData inputData = new LoginInputData(userName, password);
        interactor.execute(inputData);
    }

    /**
     * Prepares the view to show a success message.
     * <p>
     * This method is invoked if the login attempt is successful. It updates the sign-in view
     * to display the success message and then switches to the home panel after a brief delay.
     * </p>
     *
     * @param data The output data containing the success message to be displayed.
     */
    @Override
    public void prepareSuccessView(LoginOutputData data) {
        SwingUtilities.invokeLater(() -> view.showSuccess(data.getMessage()));
        Timer timer = new Timer(2000, e -> {
            frame.switchToHomePanel();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Prepares the view to show an error message.
     * <p>
     * This method is invoked if the login attempt fails. It updates the sign-in view
     * to display the error message.
     * </p>
     *
     * @param error The error message to be displayed.
     */
    @Override
    public void prepareFailView(String error) {
        view.showError(error);
    }
}
