package use_case.login;

public class LoginOutputData {
    private final boolean success;
    private final String message;

    public LoginOutputData(boolean success, String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty.");
        }
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LoginOutputData{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
