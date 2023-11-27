//package views;
//
//import presenter.SignupPresenter;
//import use_case.signup.SignUpInputData;
//import use_case.signup.SignupInteractor;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class RegisterPanel extends JPanel {
//    private JLabel welcomeLabel;
//    private JLabel registerLabel;
//    private JLabel firstNameLabel;
//    private JTextField firstNameText;
//    private JLabel lastNameLabel;
//    private JTextField lastNameText;
//    private JLabel userLabel;
//    private JTextField userText;
//    private JLabel emailLabel;
//    private JTextField emailText;
//    private JLabel reEmailLabel;
//    private JTextField reEmailText;
//    private JLabel passwordLabel;
//    private JLabel passwordReqLabel;
//    private JPasswordField passwordText;
//    private JLabel rePasswordLabel;
//    private JPasswordField rePasswordText;
//    private JLabel success;
//    private JButton registerButton;
//
//    private MainFrame frame;
//    private SignupPresenter presenter;
//
//    public RegisterPanel(MainFrame frame) {
//        this.frame = frame;
//        this.presenter = presenter;
//        setLayout(null);
//
//        welcomeLabel = new JLabel("WELCOME!");
//        welcomeLabel.setBounds(10, 20, 100, 25);
//        Font laFont = welcomeLabel.getFont();
//        float newWelcomeSize = 18.0f;
//        welcomeLabel.setFont(laFont.deriveFont(newWelcomeSize));
//        add(welcomeLabel);
//
//        registerLabel = new JLabel("Register to continue. ");
//        registerLabel.setBounds(10, 40, 200, 25);
//        Font labelFont = registerLabel.getFont();
//        float newSize = 12.0f;
//        registerLabel.setFont(labelFont.deriveFont(newSize));
//        registerLabel.setForeground(Color.GRAY);
//        add(registerLabel);
//
//        firstNameLabel = new JLabel("First name");
//        firstNameLabel.setBounds(10, 70, 100, 25);
//        add(firstNameLabel);
//
//        firstNameText = new JTextField();
//        firstNameText.setBounds(10, 90, 100, 25);
//        add(firstNameText);
//
//        lastNameLabel = new JLabel("Last name");
//        lastNameLabel.setBounds(10, 110, 100, 25);
//        add(lastNameLabel);
//
//        lastNameText = new JTextField();
//        lastNameText.setBounds(10, 130, 100, 25);
//        add(lastNameText);
//
//        userLabel = new JLabel("Username");
//        userLabel.setBounds(10, 150, 100, 25);
//        add(userLabel);
//
//        userText = new JTextField();
//        userText.setBounds(10, 170, 200, 25);
//        add(userText);
//
//        emailLabel = new JLabel("Email");
//        emailLabel.setBounds(10, 190, 100, 25);
//        add(emailLabel);
//
//        emailText = new JTextField();
//        emailText.setBounds(10, 210, 200, 25);
//        add(emailText);
//
//        reEmailLabel = new JLabel("Confirm your email");
//        reEmailLabel.setBounds(10, 230, 300, 25);
//        add(reEmailLabel);
//
//        reEmailText = new JTextField();
//        reEmailText.setBounds(10, 250, 200, 25);
//        add(reEmailText);
//
//        passwordLabel = new JLabel("Password");
//        passwordLabel.setBounds(10, 270, 300, 25);
//        add(passwordLabel);
//
//        passwordText = new JPasswordField();
//        passwordText.setBounds(10, 290, 200, 25);
//        add(passwordText);
//
//        passwordReqLabel = new JLabel("<html>- At least 8 characters<br>" +
//                "- At least 1 lowercase character<br>" +
//                "- At least 1 uppercase character<br>" +
//                "- At least 1 number</html>");
//        passwordReqLabel.setBounds(10, 310, 250, 100);
//        passwordReqLabel.setForeground(Color.GRAY);
//        add(passwordReqLabel);
//
//        rePasswordLabel = new JLabel("Repeat Password");
//        rePasswordLabel.setBounds(10, 400, 200, 25);
//        add(rePasswordLabel);
//
//        rePasswordText = new JPasswordField();
//        rePasswordText.setBounds(10, 430, 200, 25);
//        add(rePasswordText);
//
//        registerButton = new JButton("Register");
//        registerButton.setBounds(10, 470, 80, 25);
//        registerButton.addActionListener(this);
//        add(registerButton);
//
//        success = new JLabel("");
//        success.setBounds(10, 490, 200, 25);
//        add(success);
//
//        JButton backButton = new JButton("Back");
//        backButton.setBounds(10, 520, 80, 25);
//        backButton.addActionListener(e -> frame.switchToPanel(new InitPanel(frame)));
//        add(backButton);
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String firstName = firstNameText.getText();
//        String lastName = lastNameText.getText();
//        String username = userText.getText();
//        String email = emailText.getText();
//        String reEmail = reEmailText.getText();
//        String password = new String(passwordText.getPassword());
//        String rePassword = new String(rePasswordText.getPassword());
//
//        SignUpInputData inputData = new SignUpInputData(firstName, lastName, username, email, reEmail, password, rePassword);
//
//        presenter.signupUser(inputData);
//        }
//
//    @Override
//    public void showSignupSuccess(String message) {
//        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    @Override
//    public void showSignupError(String message) {
//        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
//    }
//}
//
//
//
