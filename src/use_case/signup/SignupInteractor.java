package use_case.signup;
import interface_adapter.EmailSender.EmailSenderGateway;

/**
 * Interactor class for handling the signup process.
 * This class manages the business logic of registering new users. It validates input data such as username,
 * password, and email, interacts with the data access layer to check for existing users and create new ones,
 * and communicates results back to the output boundary.
 */
public class SignupInteractor implements SignupInputBoundary{

    private final SignupUserDataAccessInterface userDataAccess;
    private SignupOutputBoundary signupOutputBoundary;

    /**
     * Constructs a SignupInteractor with the specified data access and output boundary.
     *
     * @param userDataAccess         The data access interface for retrieving and updating user data.
     * @param signupOutputBoundary   The output boundary for presenting results to the user or the system.
     */
    public SignupInteractor(SignupUserDataAccessInterface userDataAccess,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccess = userDataAccess;
        this.signupOutputBoundary = signupOutputBoundary;
    }

    /**
     * Executes the signup process using the given input data.
     * Validates the user input and creates a new user if the validation is successful, or presents
     * error messages if validation fails.
     *
     * @param signupInputData The input data containing user registration details.
     */
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
            userDataAccess.createUser(signupInputData.getFirstName(), signupInputData.getLastName(),
                    signupInputData.getUsername(), signupInputData.getPassword(), "UNDEFINED",
                    signupInputData.getEmail());
            signupOutputBoundary.prepareSuccessView(new SignupOutputData(
                    true,
                    "Sign up successful!",
                    userDataAccess.getUserByUsername(signupInputData.getUsername()).getUserId()));

            String email = signupInputData.getEmail();
            EmailSenderGateway.sendWelcomeEmail(email);
        }
    }
}
