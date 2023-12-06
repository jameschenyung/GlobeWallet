package use_case.signup;

import Entity.User;
import interface_adapter.EmailSender.EmailSenderGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

class MockSignupUserDataAccess implements SignupUserDataAccessInterface {
    private boolean usernameTaken = false;
    private boolean passwordValid = true;

    @Override
    public User createUser(String firstName, String lastName, String username, String password, String CurrencyType, String email) {
        return new User(1, firstName, lastName, username, password, CurrencyType, email); // Simplified user creation
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return usernameTaken;
    }

    @Override
    public boolean validatePassword(String password) {
        return passwordValid;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    public void setUsernameTaken(boolean usernameTaken) {
        this.usernameTaken = usernameTaken;
    }

    public void setPasswordValid(boolean passwordValid) {
        this.passwordValid = passwordValid;
    }

    // Other required methods...
}

class MockEmailSenderGateway implements EmailSenderGateway {
    @Override
    public void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {

    }

    // Implement the methods as needed for the test
    @Override
    public void sendWelcomeEmail(String email, String name) {
        // Simulate sending a welcome email
    }

    @Override
    public void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

    }

    @Override
    public void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

    }

    @Override
    public void sendUpdateConfirmation(String email, String name) {

    }
    // Other required methods...
}

class MockSignupOutputBoundary implements SignupOutputBoundary {
    private String message;
    private boolean success;

    @Override
    public void prepareSuccessView(SignupOutputData data) {
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

class SignupInteractorTest {

    private SignupInteractor interactor;
    private MockSignupUserDataAccess dataAccess;
    private MockEmailSenderGateway emailSender;
    private MockSignupOutputBoundary outputBoundary;

    @BeforeEach
    void setUp() {
        dataAccess = new MockSignupUserDataAccess();
        emailSender = new MockEmailSenderGateway();
        outputBoundary = new MockSignupOutputBoundary();
        interactor = new SignupInteractor(dataAccess, outputBoundary, emailSender);
    }

    @Test
    void execute_SuccessfulSignup() {
        SignUpInputData inputData = new SignUpInputData("John", "Doe", "johndoe", "john@example.com",
                "john@example.com", "password123", "password123");
        interactor.execute(inputData);

        assertTrue(outputBoundary.isSuccess());
        assertEquals("Sign up successful!", outputBoundary.getMessage());
    }

    @Test
    void execute_UsernameTaken() {
        dataAccess.setUsernameTaken(true);

        SignUpInputData inputData = new SignUpInputData("John", "Doe", "johndoe", "john@example.com",
                "john@example.com", "password123", "password123");
        interactor.execute(inputData);

        assertFalse(outputBoundary.isSuccess());
        assertEquals("User already exists.", outputBoundary.getMessage());
    }

    // Additional test cases for different scenarios...
}
