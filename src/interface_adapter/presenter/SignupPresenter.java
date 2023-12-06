package interface_adapter.presenter;

import interface_adapter.EmailSender.EmailSenderGateway;
import use_case.signup.*;
import views.MainFrame;
import views.SignupView;

import javax.swing.*;

/**
 * Presenter for the sign-up process.
 * <p>
 * This class acts as the intermediary between the sign-up view and the sign-up interactor (use case).
 * It processes user input for registration and updates the sign-up view based on the result of the
 * registration attempt.
 * </p>
 */
public class SignupPresenter implements SignupOutputBoundary {
    private final SignupView view;
    private MainFrame frame;
    private SignupInteractor interactor;
    private EmailSenderGateway emailSenderGateway;

    /**
     * Constructs a new SignupPresenter.
     *
     * @param view          The view for the sign-up process.
     * @param frame         The main application window.
     * @param userDataAccess The data access interface for user registration.
     */
    public SignupPresenter(SignupView view, MainFrame frame, SignupUserDataAccessInterface userDataAccess) {
        this.view = view;
        this.frame = frame;
        this.interactor = new SignupInteractor(userDataAccess, this, emailSenderGateway);
        this.view.setPresenter(this);
    }

    /**
     * Sets the signup interactor for this presenter.
     *
     * @param interactor The SignupInteractor to be used by this presenter.
     */
    public void setInteractor(SignupInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Initiates the sign-up process.
     * <p>
     * This method is called when the user submits their registration details. It creates
     * the necessary input data and triggers the interactor to process the sign-up.
     * </p>
     *
     * @param firstName       The user's first name.
     * @param lastName        The user's last name.
     * @param username        The chosen username.
     * @param email           The user's email address.
     * @param repeatEmail     The user's email address, repeated for confirmation.
     * @param password        The chosen password.
     * @param repeatPassword  The chosen password, repeated for confirmation.
     */
    public void handleSignup(String firstName, String lastName, String username, String email, String repeatEmail, String password, String repeatPassword) {
        SignUpInputData inputData = new SignUpInputData(firstName, lastName, username, email, repeatEmail, password, repeatPassword);
        interactor.execute(inputData);
    }

    /**
     * Prepares and displays the success view.
     * <p>
     * This method is invoked if the sign-up attempt is successful. It updates the sign-up view
     * with the success message and switches to the sign-in view after a short delay.
     * </p>
     *
     * @param data The output data containing the success message and other relevant information.
     */
    @Override
    public void prepareSuccessView(SignupOutputData data) {
        SwingUtilities.invokeLater(() -> view.showSuccess(data.getMessage()));
        Timer timer = new Timer(2000, e -> {
            frame.switchToSignInView();
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Prepares and displays the failure view.
     * <p>
     * This method is invoked if the sign-up attempt fails. It updates the sign-up view
     * with the error message.
     * </p>
     *
     * @param error The error message to be displayed.
     */
    @Override
    public void prepareFailView(String error) {
        SwingUtilities.invokeLater(() -> view.showError(error));
    }
}

