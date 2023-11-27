package use_case.signup;

public class SignupOutputData {
    private final boolean success;
    private final String message;
    private final Integer userId;

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

