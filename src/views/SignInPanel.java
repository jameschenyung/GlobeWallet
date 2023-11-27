package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPanel extends JPanel implements ActionListener {
    private JLabel welcomeLabel;
    private JLabel signinLabel;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userText;
    private JLabel success;
    private JButton logInButton;
    private JPasswordField passwordText;

    private MainFrame frame;

    public SignInPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(null);

        welcomeLabel = new JLabel("WELCOME!");
        welcomeLabel.setBounds(10, 20, 100, 25);
        Font laFont = welcomeLabel.getFont();
        float newWelcomeSize = 18.0f;
        welcomeLabel.setFont(laFont.deriveFont(newWelcomeSize));
        add(welcomeLabel);

        signinLabel = new JLabel("Log in to continue. ");
        signinLabel.setBounds(10, 40, 200, 25);
        Font labelFont = signinLabel.getFont();
        float newSize = 12.0f;
        signinLabel.setFont(labelFont.deriveFont(newSize));
        signinLabel.setForeground(Color.GRAY);
        add(signinLabel);

        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 70, 100, 25);
        add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(10, 90, 200, 25);
        add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 110, 100, 25);
        add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(10, 130, 200, 25);
        add(passwordText);

        logInButton = new JButton("Login");
        logInButton.setBounds(10, 160, 80, 25);
        logInButton.addActionListener(this);
        add(logInButton);

        success = new JLabel("");
        success.setBounds(10, 180, 300, 25);
        add(success);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 210, 80, 25);
        backButton.addActionListener(e -> frame.switchToPanel(new InitPanel(frame)));
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = new String(passwordText.getPassword());

        if (user.equals("test") && password.equals("Test1234")) {
            success.setText("Login successful!");
            frame.switchToPanel(new HomePanel(frame));
        } else {
            success.setText("Login failed!");
        }
    }
}
