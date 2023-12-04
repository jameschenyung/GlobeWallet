package use_case.signup;

/**
 * Data class that encapsulates the output data for the user signup operation.
 * This class holds information about the outcome of a signup attempt, including a success flag,
 * a message detailing the result, and the user ID of the newly created user (if successful).
 */
public class SignupOutputData {
    private final boolean success;
    private final String message;
    private final Integer userId;


    /**
     * Constructs a SignupOutputData instance with specified success status, message, and user ID.
     *
     * @param success Indicates whether the signup operation was successful.
     * @param message A message describing the outcome of the signup operation.
     * @param userId  The unique identifier of the newly created user, if signup was successful.
     */
    public SignupOutputData(boolean success, String message, int userId) {
        this.success = success;
        this.message = message;
        this.userId = userId;
    }

    // Getters and possibly setters if mutable
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Integer getUserId() {
        return userId;
    }
}

