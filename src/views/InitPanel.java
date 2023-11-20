package views;

import javax.swing.JPanel;
import javax.swing.JButton;

public class InitPanel extends JPanel {

    private MainFrame frame;

    public InitPanel(MainFrame frame) {
        this.frame = frame;

        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(e -> frame.switchToPanel(new SignInPanel(frame)));
        add(signInButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> frame.switchToPanel(new RegisterPanel(frame)));
        add(registerButton);
    }
}
