package use_case.updateUser;

import Entity.User;
import interface_adapter.EmailSender.EmailSenderGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MockUpdateUserDataAccess implements UpdateUserDataAccessInterface {
    private boolean usernameTaken = false;
    private boolean passwordValid = true;
    private final int currentUserId = 1;

    @Override
    public boolean isUsernameTaken(String username) {
        return usernameTaken;
    }

    @Override
    public boolean validatePassword(String password) {
        return passwordValid;
    }

    @Override
    public void updateUser(int id, String email, String username, String password) {
        // Simulate updating user
    }

    @Override
    public User getUserByUsername(String username) {
        return new User(1, "a", "b", username, "abab", "UNDEFINED", "abab");
    }

    @Override
    public Integer getCurrentUserId() {
        return currentUserId;
    }

    @Override
    public Integer getUserIdFromUserName(String username) {
        return null;
    }

    @Override
    public String getFullName(Integer userid) {
        return null;
    }

    // Setters for test conditions
    public void setUsernameTaken(boolean usernameTaken) {
        this.usernameTaken = usernameTaken;
    }

    public void setPasswordValid(boolean passwordValid) {
        this.passwordValid = passwordValid;
    }

    // Other methods...
}

class MockEmailSenderGateway implements EmailSenderGateway {
    @Override
    public void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {

    }

    @Override
    public void sendWelcomeEmail(String email, String name) {

    }

    @Override
    public void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

    }

    @Override
    public void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

    }

    @Override
    public void sendUpdateConfirmation(String email, String name) {
        // Simulate sending email
    }
}

class MockUpdateUserOutputBoundary implements UpdateUserOutputBoundary {
    private boolean success;
    private String message;

    @Override
    public void prepareSuccessView(UpdateUserOutputData data) {
        this.success = data.isSuccess();
        this.message = data.getMessage();
    }

    @Override
    public void prepareFailView(String message) {
        this.success = false;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}


class UpdateUserInteractorTest {

    private MockUpdateUserDataAccess dataAccess;
    private MockEmailSenderGateway emailSenderGateway;
    private MockUpdateUserOutputBoundary outputBoundary;
    private UpdateUserInteractor interactor;

    @BeforeEach
    void setUp() {
        dataAccess = new MockUpdateUserDataAccess();
        emailSenderGateway = new MockEmailSenderGateway();
        outputBoundary = new MockUpdateUserOutputBoundary();
        interactor = new UpdateUserInteractor(dataAccess, outputBoundary, emailSenderGateway);
    }

    @Test
    void execute_SuccessfulUpdate() throws Exception {
        UpdateUserInputData inputData = new UpdateUserInputData("newemail@example.com", "newusername",
                "newpassword", "newpassword");

        interactor.execute(inputData);
        assertEquals("Update successful!", "Update successful!");
    }

    @Test
    void execute_PasswordMismatch() throws Exception {
        UpdateUserInputData inputData = new UpdateUserInputData("newemail@example.com", "newusername",
                "newpassword", "differentpassword");

        interactor.execute(inputData);

        assertFalse(outputBoundary.isSuccess());
        assertEquals("Passwords don't match", outputBoundary.getMessage());
    }

    @Test
    void execute_UsernameAlreadyTaken() throws Exception {
        dataAccess.setUsernameTaken(true);
        UpdateUserInputData inputData = new UpdateUserInputData("newemail@example.com", "existingusername",
                "newpassword", "newpassword");

        interactor.execute(inputData);

        assertFalse(outputBoundary.isSuccess());
        assertEquals("Username already exists.", outputBoundary.getMessage());
    }

    // Additional test cases for different scenarios...
}
