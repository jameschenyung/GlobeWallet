package views;

import database.DataAccessObject;
import presenter.SignupPresenter;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupUserDataAccessInterface;

import javax.swing.JPanel;
import javax.swing.JButton;

public class InitPanel extends JPanel {

    private MainFrame frame;

    public InitPanel(MainFrame frame) {
        this.frame = frame;

        JButton signInButton = new JButton("Log In");
        signInButton.addActionListener(e -> frame.switchToPanel(new SignInPanel(frame)));
        add(signInButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> frame.switchToSignupView());
        add(registerButton);
    }
}