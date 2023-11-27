package views;

import javax.swing.*;

import com.sun.tools.javac.Main;
import database.DataAccessObject;
import objects.User;
import presenter.LoginPresenter;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignUpInputData;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import presenter.SignupPresenter;

public class MainFrame extends JFrame {
    private SignupUserDataAccessInterface userDataAccess = new DataAccessObject();
    private LoginUserDataAccessInterface loginUserDataAccess = new DataAccessObject();
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
        SignupPresenter signupPresenter = new SignupPresenter(signupView, this, userDataAccess);
        signupView.setPresenter(signupPresenter);
        SignupInteractor signupInteractor = new SignupInteractor(userDataAccess, signupPresenter);
        signupPresenter.setInteractor(signupInteractor);
        switchToPanel(signupView);
    }

    public void switchToSignInView() {
        SignInPanel signInView = new SignInPanel(this);
        LoginPresenter loginPresenter = new LoginPresenter(signInView, this, loginUserDataAccess);
        signInView.setPresenter(loginPresenter);
        LoginInteractor loginInteractor = new LoginInteractor(loginPresenter, loginUserDataAccess);
        loginPresenter.setInteractor(loginInteractor);
        switchToPanel(signInView);
    }

    public void switchToHomePanel() {
        HomePanel homePanel = new HomePanel(this);
        switchToPanel(homePanel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

