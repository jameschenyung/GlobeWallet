package views;

import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * A JPanel representing the initial screen of the application.
 * This panel provides users with options to either log in or register. It includes buttons
 * for navigating to the login and registration views.
 */
public class InitPanel extends JPanel {

    private MainFrame frame;

    /**
     * Constructs an InitPanel associated with the given MainFrame.
     * This panel contains buttons for logging in and registering, which, when clicked,
     * switch the view to the respective panels for these actions.
     *
     * @param frame The MainFrame that this panel is a part of.
     */
    public InitPanel(MainFrame frame) {
        this.frame = frame;

        JButton signInButton = new JButton("Log In");
        signInButton.addActionListener(e -> frame.switchToSignInView());
        add(signInButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> frame.switchToSignupView());
        add(registerButton);
    }
}