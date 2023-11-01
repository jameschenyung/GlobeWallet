package views;

import views.RegisterPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage implements ActionListener {
    private static JLabel registerLabel;
    private static JLabel firstName;
    private static JTextField firstNameText;
    private static JLabel lastName;
    private static JTextField lastNameText;
    private static JLabel email;
    private static JTextField emailText;
    private static JLabel rePassword;
    private static JPasswordField rePasswordText;
    private  static JLabel userLabel;
    private static JLabel passwordLabel;
    private static JTextField userText;
    private static JLabel success;
    private static JButton RegisterButton;
    private static JPasswordField passwordText;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel RegisterPanel = new JPanel();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(RegisterPanel);
        frame.setVisible(true);

        RegisterPanel.setLayout(null);


        registerLabel = new JLabel("REGISTER");
        registerLabel.setBounds(10, 20, 100, 25);
        RegisterPanel.add(registerLabel);

        firstName = new JLabel("First name");
        firstName.setBounds(10, 50, 100, 25);
        RegisterPanel.add(firstName);

        firstNameText = new JTextField();
        firstNameText.setBounds(10, 70, 100, 25);
        RegisterPanel.add(firstNameText);

        lastName = new JLabel("Last name");
        lastName.setBounds(10, 90, 100, 25);
        RegisterPanel.add(lastName);

        lastNameText = new JTextField();
        lastNameText.setBounds(10, 110, 100, 25);
        RegisterPanel.add(lastNameText);

        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 130, 100, 25);
        RegisterPanel.add(userLabel);

        userText = new JTextField();
        userText.setBounds(10, 150, 200, 25);
        RegisterPanel.add(userText);

        email = new JLabel("Email");
        email.setBounds(10, 170, 200, 25);
        RegisterPanel.add(email);

        emailText = new JTextField();
        emailText.setBounds(10, 190, 200, 25);
        RegisterPanel.add(emailText);

        passwordLabel = new JLabel("Password (8 or more characters)");
        passwordLabel.setBounds(10, 210, 300, 25);
        RegisterPanel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(10, 230, 200, 25);
        RegisterPanel.add(passwordText);

        rePassword = new JLabel("Repeat Password");
        rePassword.setBounds(10, 250, 200, 25);
        RegisterPanel.add(rePassword);

        rePasswordText = new JPasswordField();
        rePasswordText.setBounds(10, 270, 200, 25);
        RegisterPanel.add(rePasswordText);


        RegisterButton = new JButton("Register");
        RegisterButton.setBounds(10, 300, 80, 25);
        RegisterButton.addActionListener(new RegisterPage());
        RegisterPanel.add(RegisterButton);

        success = new JLabel("");
        success.setBounds(10, 330, 200, 25);
        RegisterButton.add(success);

        frame.setVisible(true);
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
