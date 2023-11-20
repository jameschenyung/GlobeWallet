package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel implements ActionListener {
    private JLabel registerLabel;
    private JLabel firstNameLabel;
    private JTextField firstNameText;
    private JLabel lastNameLabel;
    private JTextField lastNameText;
    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JLabel rePasswordLabel;
    private JPasswordField rePasswordText;
    private JLabel success;
    private JButton registerButton;

    private MainFrame frame;

    public RegisterPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(null);

        registerLabel = new JLabel("REGISTER");
        registerLabel.setBounds(10, 20, 100, 25);
        add(registerLabel);

        firstNameLabel = new JLabel("First name");
        firstNameLabel.setBounds(10, 50, 100, 25);
        add(firstNameLabel);

        firstNameText = new JTextField();
        firstNameText.setBounds(10, 70, 100, 25);
        add(firstNameText);

        lastNameLabel = new JLabel("Last name");
        lastNameLabel.setBounds(10, 90, 100, 25);
        add(lastNameLabel);

        lastNameText = new JTextField();
        lastNameText.setBounds(10, 110, 100, 25);
        add(lastNameText);

        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 130, 100, 25);
        add(userLabel);

        userText = new JTextField();
        userText.setBounds(10, 150, 200, 25);
        add(userText);

        passwordLabel = new JLabel("Password (8 or more characters)");
        passwordLabel.setBounds(10, 170, 300, 25);
        add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(10, 190, 200, 25);
        add(passwordText);

        rePasswordLabel = new JLabel("Repeat Password");
        rePasswordLabel.setBounds(10, 210, 200, 25);
        add(rePasswordLabel);

        rePasswordText = new JPasswordField();
        rePasswordText.setBounds(10, 230, 200, 25);
        add(rePasswordText);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 260, 80, 25);
        registerButton.addActionListener(this);
        add(registerButton);

        success = new JLabel("");
        success.setBounds(10, 290, 200, 25);
        add(success);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 330, 80, 25);
        backButton.addActionListener(e -> frame.switchToPanel(new InitPanel(frame)));
        add(backButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        String repassword = rePasswordText.getText();

        if (user.equals("test") && (password.equals("test1234")) && (repassword.equals(password))) {
            success.setText("Register successful!");
        }
    }
}


