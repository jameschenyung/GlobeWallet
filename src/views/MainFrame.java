package views;

import javax.swing.*;

import com.sun.tools.javac.Main;
import database.DataAccessObject;
import objects.User;
import use_case.signup.SignUpInputData;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import presenter.SignupPresenter;

public class MainFrame extends JFrame {

    // Assuming DataAccessObject implements SignupUserDataAccessInterface
    private SignupUserDataAccessInterface userDataAccess = new DataAccessObject();
    private SignupPresenter signupPresenter;

    public MainFrame() {
        setTitle("Globe Wallet");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        switchToPanel(new InitPanel(this));
        setVisible(true);
    }

    public void switchToPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }


    public void switchToSignupView() {
        SignupView signupView = new SignupView(this);
        SignupPresenter signupPresenter = new SignupPresenter(signupView, userDataAccess);
        signupView.setPresenter(signupPresenter);
        SignupInteractor signupInteractor = new SignupInteractor(userDataAccess, signupPresenter);
        signupPresenter.setInteractor(signupInteractor);
        switchToPanel(signupView);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
