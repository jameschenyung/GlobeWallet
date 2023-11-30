package use_case.addAccount;

public class AddAccountOutputData {
    private boolean success;
    private String message;

    public AddAccountOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
