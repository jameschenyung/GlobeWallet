import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPage implements ActionListener {
    private  static JLabel userLabel;
    private static JLabel passwordLabel;
    private static JTextField userText;
    private static JLabel success;
    private static JButton logInButton;
    private static JPasswordField passwordText;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel SignInPanel = new JPanel();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(SignInPanel);
        frame.setVisible(true);

        SignInPanel.setLayout(null);

        userLabel = new JLabel("User Name");
        userLabel.setBounds(10, 20, 100, 25);
        SignInPanel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 200, 25);
        SignInPanel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 100, 25);
        SignInPanel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 200, 25);
        SignInPanel.add(passwordText);

        logInButton = new JButton("Login");
        logInButton.setBounds(10, 80, 80, 25);
        logInButton.addActionListener(new SignInPage());
        SignInPanel.add(logInButton);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        SignInPanel.add(success);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();

        if (user.equals("test") && (password.equals("test"))) {
            success.setText("Login successful!");
        }
    }
}
