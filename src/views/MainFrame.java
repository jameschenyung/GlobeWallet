package views;

import javax.swing.*;

import com.sun.tools.javac.Main;
import database.DataAccessObject;
import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import interface_adapter.CurrencyConverter.PolygonCurrencyConversionGateway;
import objects.User;
import presenter.LoginPresenter;
import presenter.ReceiveMoneyPresenter;
import presenter.SendMoneyPresenter;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;
import use_case.receiveMoney.receiveMoneyDataAccessInterface;
import use_case.receiveMoney.receiveMoneyInputBoundary;
import use_case.receiveMoney.receiveMoneyInteractor;
import use_case.receiveMoney.receiveMoneyOutputBoundary;
import use_case.sendmoneytransfer.SendMoneyInteractor;
import use_case.sendmoneytransfer.SendMoneyOutputBoundary;
import use_case.sendmoneytransfer.SendMoneyUserDataAccessInterface;
import use_case.signup.SignUpInputData;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import presenter.SignupPresenter;

public class MainFrame extends JFrame {
    private SignupUserDataAccessInterface userDataAccess = new DataAccessObject();
    private SendMoneyUserDataAccessInterface sendMoneyUserDataAccess = new DataAccessObject();
    private LoginUserDataAccessInterface loginUserDataAccess = new DataAccessObject();
    private SignupPresenter signupPresenter;
    private SendMoneyOutputBoundary sendMoneyOutputBoundary;
    private ReceiveMoneyPresenter receiveMoneyPresenter;
    private final DataAccessObject dataAccess = new DataAccessObject();
    private final CurrencyConversionGateway currencyConversionGateway = new PolygonCurrencyConversionGateway();


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

    public void switchToMoneyTransferPanel() {
        MoneyTransferPanel moneyTransferPanel = new MoneyTransferPanel(this);
        SendMoneyPresenter sendMoneyPresenter = new SendMoneyPresenter(moneyTransferPanel, this);
        SendMoneyInteractor sendMoneyInteractor = new SendMoneyInteractor(sendMoneyUserDataAccess, sendMoneyPresenter, currencyConversionGateway);
        sendMoneyPresenter.setSendMoneyInteractor(sendMoneyInteractor);
        moneyTransferPanel.setPresenter(sendMoneyPresenter);
        switchToPanel(moneyTransferPanel);
    }

    public void switchToReceiveMoneyPanel() {
        receiveMoneyDataAccessInterface dataAccess = new DataAccessObject();
        receiveMoneyOutputBoundary presenterOutputBoundary = new ReceiveMoneyPresenter(null, null);

        receiveMoneyInteractor interactor = new receiveMoneyInteractor(dataAccess, presenterOutputBoundary);
        ReceiveMoneyPanel receiveMoneyPanel = new ReceiveMoneyPanel(this, receiveMoneyPresenter);
        ReceiveMoneyPresenter presenter = new ReceiveMoneyPresenter(receiveMoneyPanel, interactor);
        receiveMoneyPanel.setPresenter(presenter);
        switchToPanel(receiveMoneyPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

