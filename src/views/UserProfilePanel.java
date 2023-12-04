package views;

import presenter.UpdateUserPresenter;

import javax.swing.*;
import java.awt.*;

public class UserProfilePanel extends JPanel {
    private MainFrame frame;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JButton updateButton;
    private UpdateUserPresenter presenter;

    public void setPresenter(UpdateUserPresenter presenter) {
        this.presenter = presenter;
    }

    public UserProfilePanel(MainFrame frame) {
        this.frame = frame;
        // Set the layout manager for this panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchToPanel(new AccountPanel(frame)));

        // Initialize form elements
        emailField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        repeatPasswordField = new JPasswordField(20);
        updateButton = new JButton("Update Profile");

        // Set up layout constraints
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        // Email label and field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        add(emailField, gbc);

        // Username label and field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        // Password label and field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        // Repeat password label and field
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Repeat Password:"), gbc);

        gbc.gridx = 1;
        add(repeatPasswordField, gbc);

        // Update button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton.addActionListener(e -> {
            // Call the presenter to update the user info
            String email = emailField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String repeatPassword = new String(repeatPasswordField.getPassword());
            presenter.updateUserInfo(email, username, password, repeatPassword);
        });
        add(updateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(backButton, gbc);
    }


    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
