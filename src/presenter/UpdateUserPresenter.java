package presenter;

import use_case.updateUser.*;
import views.UserProfilePanel;

import javax.swing.*;

public class UpdateUserPresenter implements UpdateUserOutputBoundary {
    private UserProfilePanel view;
    private UpdateUserInputBoundary interactor;

    public UpdateUserPresenter(UserProfilePanel view, UpdateUserDataAccessInterface updateUserDataAccess) {
        this.view = view;
        this.interactor = new UpdateUserInteractor(updateUserDataAccess, this);
    }

    public void updateUserInfo(String email, String username, String newPassword, String repeatPassword) {
        try {
            UpdateUserInputData inputData = new UpdateUserInputData(email, username, newPassword, repeatPassword);
            interactor.execute(inputData);
        } catch (Exception e) {
            view.showError("An error occurred: " + e.getMessage());
        }
    }
    @Override
    public void prepareFailView(String error) {
        SwingUtilities.invokeLater(() -> view.showError(error));
    }

    @Override
    public void prepareSuccessView(UpdateUserOutputData user) {
        SwingUtilities.invokeLater(() -> {
            view.showSuccess("User Profile Updated Successfully");
        });
    }
}
