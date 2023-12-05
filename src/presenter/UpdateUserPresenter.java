package presenter;

import interface_adapter.EmailSender.EmailSenderGateway;
import use_case.updateUser.*;
import views.UserProfilePanel;

import javax.swing.*;

/**
 * The {@code UpdateUserPresenter} class serves as the presenter in the MVP (Model-View-Presenter) pattern
 * for updating user information. It acts as an intermediary between the view ({@code UserProfilePanel}) and
 * the use case interactor ({@code UpdateUserInteractor}), handling the communication between the view and the
 * business logic for updating user details.
 */
public class UpdateUserPresenter implements UpdateUserOutputBoundary {
    private UserProfilePanel view;
    private UpdateUserInputBoundary interactor;
    private EmailSenderGateway emailSenderGateway;

    /**
     * Constructs an {@code UpdateUserPresenter} with the specified user profile view and data access interface.
     *
     * @param view                   The {@code UserProfilePanel} that this presenter is responsible for.
     * @param updateUserDataAccess   An implementation of {@code UpdateUserDataAccessInterface} to interact with
     *                               user data storage.
     */
    public UpdateUserPresenter(UserProfilePanel view, UpdateUserDataAccessInterface updateUserDataAccess, EmailSenderGateway emailSenderGateway) {
        this.view = view;
        this.emailSenderGateway = emailSenderGateway;
        this.interactor = new UpdateUserInteractor(updateUserDataAccess, this, emailSenderGateway);
        this.view.setPresenter(this);
    }

    /**
     * Initiates the user information update process using the provided user details.
     * If the update process encounters errors, it will instruct the view to display the appropriate message.
     *
     * @param email          The email address of the user, used as an identifier for update operations.
     * @param username       The new username to be updated.
     * @param newPassword    The new password for the user.
     * @param repeatPassword A repeated entry of the new password for confirmation purposes.
     */
    public void updateUserInfo(String email, String username, String newPassword, String repeatPassword) {
        try {
            UpdateUserInputData inputData = new UpdateUserInputData(email, username, newPassword, repeatPassword);
            interactor.execute(inputData);
        } catch (Exception e) {
            view.showError("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Handles the case where updating user information fails. It triggers a UI update to show an error message.
     *
     * @param error A string describing the error that occurred during the update process.
     */
    @Override
    public void prepareFailView(String error) {
        SwingUtilities.invokeLater(() -> view.showError(error));
    }

    /**
     * Handles the case where updating user information succeeds. It triggers a UI update to show a success message.
     *
     * @param user The {@code UpdateUserOutputData} containing the updated user information.
     */
    @Override
    public void prepareSuccessView(UpdateUserOutputData user) {
        SwingUtilities.invokeLater(() -> {
            view.showSuccess("User Profile Updated Successfully");
        });
    }
}
