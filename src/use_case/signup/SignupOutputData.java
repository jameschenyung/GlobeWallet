package use_case.signup;

public class SignupOutputData {
    private final boolean success;
    private final String message;
    private final String userId; // Optional, depending on your use case

    public SignupOutputData(boolean success, String message, String userId) {
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

    public String getUserId() {
        return userId;
    }
}

