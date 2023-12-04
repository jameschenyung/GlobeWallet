package use_case.updateUser;

public class UpdateUserOutputData {
    private final boolean success;
    private final String message;
    private final Integer userId;

    public UpdateUserOutputData(boolean success, String message, int userId) {
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
