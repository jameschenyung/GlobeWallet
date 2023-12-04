package use_case.login;

/**
 * Interactor class for the login use case.
 * This class handles the business logic of authenticating users. It interacts with the data access layer
 * to validate user credentials and communicates with the output boundary to present results.
 */
public class LoginInteractor implements LoginInputBoundary {
    final LoginOutputBoundary outputBoundary;
    final LoginUserDataAccessInterface userDataAccess;

    /**
     * Constructs a LoginInteractor with the specified output boundary and user data access interface.
     *
     * @param outputBoundary The output boundary to present the results of the login process.
     * @param userDataAccess The data access interface to handle user authentication and data retrieval.
     */
    public LoginInteractor(LoginOutputBoundary outputBoundary, LoginUserDataAccessInterface userDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataAccess = userDataAccess;
    }

    /**
     * Executes the login process using the given input data.
     * Validates the user credentials and presents the outcome through the output boundary.
     *
     * @param inputData The input data containing the username and password for login.
     */
    @Override
    public void execute(LoginInputData inputData) {
        if (inputData == null || inputData.getUsername() == null || inputData.getPassword() == null) {
            outputBoundary.prepareFailView("Invalid input.");
        }
        else if (userDataAccess.getUserByUsername(inputData.getUsername()) == null ||
                !userDataAccess.checkPassword(inputData.getUsername(), inputData.getPassword())) {
            outputBoundary.prepareFailView("Invalid username or password.");
        }
        else {
            userDataAccess.setCurrentUser(inputData.getUsername(),
                    userDataAccess.getUserByUsername(inputData.getUsername()).getUserId());
            outputBoundary.prepareSuccessView(new LoginOutputData(true, "Log in successful."));
        }
    }
}
