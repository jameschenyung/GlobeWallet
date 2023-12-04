package use_case.addAccount;

/**
 * Data class that encapsulates the output data for the "Add Account" use case.
 * This class holds information about the outcome of the attempt to add a new account,
 * including a success flag and a message describing the result.
 */
public class AddAccountOutputData {
    private boolean success;
    private String message;

    /**
     * Constructs an AddAccountOutputData instance with specified success status and message.
     *
     * @param success The success flag indicating whether the account addition was successful.
     * @param message The message describing the outcome of the account addition process.
     */
    public AddAccountOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Gets the success status of the account addition process.
     *
     * @return True if the account addition was successful, false otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the message describing the outcome of the account addition process.
     *
     * @return The message describing the result.
     */
    public String getMessage() {
        return message;
    }
}
