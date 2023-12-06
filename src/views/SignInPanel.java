package views;

import interface_adapter.presenter.LoginPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A JPanel representing the sign-in screen of the application.
 * This panel allows users to enter their username and password and attempt to log in.
 * It is integrated with a LoginPresenter to handle the login process.
 */
public class SignInPanel extends JPanel {
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton logInButton;
    private LoginPresenter presenter;
    private MainFrame frame;

    /**
     * Constructs a SignInPanel associated with the given MainFrame.
     * Initializes the panel with UI components for username and password input,
     * along with a login button to initiate the login process.
     *
     * @param frame The MainFrame that this panel is a part of.
     */
    public SignInPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Username:"));
        userText = new JTextField(20);
        add(userText);

        add(new JLabel("Password:"));
        passwordText = new JPasswordField();
        add(passwordText);

        logInButton = new JButton("Login");
        logInButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            if (presenter != null) {
                presenter.handleLogin(username, password);
            }
        });
        add(logInButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchToPanel(new InitPanel(frame)));
        add(backButton);
    }

    /**
     * Sets the LoginPresenter for this panel.
     *
     * @param presenter The LoginPresenter to handle login actions and logic.
     */
    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Displays a success message dialog and navigates to the home panel upon successful login.
     *
     * @param message The success message to be displayed.
     */
    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Success", JOptionPane.INFORMATION_MESSAGE);
        frame.switchToPanel(new HomePanel(frame));
    }

    /**
     * Displays an error message dialog in case of a login error.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Login Error", JOptionPane.ERROR_MESSAGE);
    }
}
