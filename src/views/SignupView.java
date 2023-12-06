package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interface_adapter.presenter.SignupPresenter;

/**
 * A JPanel representing the signup screen of the application.
 * This panel provides input fields for user registration details such as name, username, email, and password,
 * along with a signup button to initiate the registration process. It is linked with a SignupPresenter
 * to handle the signup logic.
 */
public class SignupView extends JPanel {
    private JTextField firstNameField, lastNameField, usernameField, emailField, repeatEmailField;
    private JPasswordField passwordField, repeatPasswordField;
    private JButton signupButton;
    private JLabel errorLabel;
    private SignupPresenter presenter;
    private JButton backButton;

    /**
     * Constructs a SignupView associated with the given MainFrame.
     * Initializes the panel with UI components for user registration inputs and a signup button.
     *
     * @param frame The MainFrame that this panel is a part of.
     */
    public SignupView(MainFrame frame) {
        setLayout(new GridLayout(0, 2));

        firstNameField = new JTextField();
        lastNameField = new JTextField();
        usernameField = new JTextField();
        emailField = new JTextField();
        repeatEmailField = new JTextField();
        passwordField = new JPasswordField();
        repeatPasswordField = new JPasswordField();
        signupButton = new JButton("Sign Up");
        errorLabel = new JLabel();
        backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchToPanel(new InitPanel(frame)));

        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Confirm Email:"));
        add(repeatEmailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Confirm Password:"));
        add(repeatPasswordField);
        add(signupButton);
        add(errorLabel);
        add(backButton);


        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = usernameField.getText();
                String email = emailField.getText();
                String repeatEmail = repeatEmailField.getText();
                String password = new String(passwordField.getPassword());
                String repeatPassword = new String(repeatPasswordField.getPassword());

                if (presenter != null) {
                    presenter.handleSignup(firstName, lastName, username, email, repeatEmail, password, repeatPassword);
                }
            }
        });
    }

    /**
     * Sets the SignupPresenter for this panel.
     *
     * @param presenter The SignupPresenter to handle the signup actions and logic.
     */
    public void setPresenter(SignupPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Displays a success message dialog upon successful signup.
     *
     * @param message The success message to be displayed.
     */
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Signup Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays an error message dialog in case of a signup error.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Signup Error", JOptionPane.ERROR_MESSAGE);
    }
}

