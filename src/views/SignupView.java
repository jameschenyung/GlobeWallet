package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import presenter.SignupPresenter;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class SignupView extends JPanel {
    private JTextField firstNameField, lastNameField, usernameField, emailField, repeatEmailField;
    private JPasswordField passwordField, repeatPasswordField;
    private JButton signupButton;
    private JLabel errorLabel;
    private SignupPresenter presenter;
    private JButton backButton;

    public SignupView(MainFrame frame) {
        setLayout(new GridLayout(0, 2)); // Use GridLayout for simplicity

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

        // Add components to the panel
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

        // Setup button action
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

    public void setPresenter(SignupPresenter presenter) {
        this.presenter = presenter;
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Signup Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Signup Error", JOptionPane.ERROR_MESSAGE);
    }
}

