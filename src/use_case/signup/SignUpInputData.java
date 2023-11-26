package use_case.signup;

public class SignUpInputData {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String repeatEmail;
    private String password;
    private String repeatPassword;

    public SignUpInputData(String firstName, String lastName, String username, String email,
                           String repeatEmail, String password, String repeatPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.repeatEmail = repeatEmail;
        this.password = password;
        this.repeatPassword = repeatPassword;

    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRepeatEmail() {
        return repeatEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRepeatEmail(String repeatEmail) {
        this.repeatEmail = repeatEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
