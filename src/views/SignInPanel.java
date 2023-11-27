package views;

import presenter.LoginPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPanel extends JPanel {
    private JTextField userText;
    private JPasswordField passwordText;
    private JButton logInButton;
    private LoginPresenter presenter;
    private MainFrame frame;

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

    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Login Success", JOptionPane.INFORMATION_MESSAGE);
        frame.switchToPanel(new HomePanel(frame));
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Login Error", JOptionPane.ERROR_MESSAGE);
    }
}
