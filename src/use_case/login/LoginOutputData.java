package use_case.login;
/**
 * Data class that encapsulates the output data for the login process.
 * This class holds information about the outcome of a login attempt, including a success flag
 * and a message detailing the result.
 */

public class LoginOutputData {
    private final boolean success;
    private final String message;

    /**
     * Constructs a LoginOutputData instance with specified success status and message.
     *
     * @param success The success flag indicating whether the login attempt was successful.
     * @param message The message detailing the outcome of the login attempt.
     * @throws IllegalArgumentException if the provided message is null or empty.
     */
    public LoginOutputData(boolean success, String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty.");
        }
        this.success = success;
        this.message = message;
    }

    /**
     * Gets the success status of the login attempt.
     *
     * @return True if the login attempt was successful, false otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the message detailing the outcome of the login attempt.
     *
     * @return The message detailing the result of the login attempt.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns a string representation of the LoginOutputData.
     *
     * @return A string representation of the LoginOutputData.
     */
    @Override
    public String toString() {
        return "LoginOutputData{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
