package use_case.signup;
import interface_adapter.EmailSender.EmailSenderGateway;

public class SignupInteractor implements SignupInputBoundary{

    private final SignupUserDataAccessInterface userDataAccess;
    private SignupOutputBoundary signupOutputBoundary;
    private EmailSenderGateway emailSenderGateway;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccess,
                            SignupOutputBoundary signupOutputBoundary, EmailSenderGateway emailSenderGateway) {
        this.userDataAccess = userDataAccess;
        this.signupOutputBoundary = signupOutputBoundary;
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void execute(SignUpInputData signupInputData) {
        if (!signupInputData.getRepeatPassword().equals(signupInputData.getPassword())) {
            signupOutputBoundary.prepareFailView("Passwords don't match.");
        }

        else if (!signupInputData.getEmail().equals(signupInputData.getRepeatEmail())) {
            signupOutputBoundary.prepareFailView("Emails don't match.");
        }

        else if (userDataAccess.isUsernameTaken(signupInputData.getUsername())) {
            // Handle the case where username is already taken
            signupOutputBoundary.prepareFailView("User already exists.");
        }

        else if (!userDataAccess.validatePassword(signupInputData.getPassword())) {
            // Handle the case where the password does not meet the policy
            signupOutputBoundary.prepareFailView("Invalid password.");
        }

        // Assuming the createUser method handles email within the User object creation
        else{
            String email = signupInputData.getEmail();
            String name = signupInputData.getFirstName();
            emailSenderGateway.sendWelcomeEmail(email, name);

            userDataAccess.createUser(signupInputData.getFirstName(), signupInputData.getLastName(),
                    signupInputData.getUsername(), signupInputData.getPassword(), "UNDEFINED",
                    signupInputData.getEmail());
            signupOutputBoundary.prepareSuccessView(new SignupOutputData(
                    true,
                    "Sign up successful!",
                    userDataAccess.getUserByUsername(signupInputData.getUsername()).getUserId()));
        }
    }
}
