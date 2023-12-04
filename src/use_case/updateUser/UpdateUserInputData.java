package use_case.updateUser;

public class UpdateUserInputData {
    private String newEmail;
    private String newUsername;
    private String newPassword;
    private String repeatNewPassword;

    public UpdateUserInputData(Integer id, String newEmail, String newUsername, String newPassword, String repeatNewPassword) {
        this.newEmail = newEmail;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.repeatNewPassword = repeatNewPassword;
    }

    // getters
    public String getNewEmail() {

        return newEmail;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }

    // setters
    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public void setNewEmail(String newEmail) {

        this.newEmail = newEmail;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setRepeatNewPassword(String repeatNewPassword) {
        this.repeatNewPassword = repeatNewPassword;
    }
}
