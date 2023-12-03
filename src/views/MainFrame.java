package views;

import javax.swing.*;

import com.sun.tools.javac.Main;
import database.DataAccessObject;
import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import interface_adapter.CurrencyConverter.PolygonCurrencyConversionGateway;
import interface_adapter.EmailSender.EmailSender;
import interface_adapter.EmailSender.EmailSenderGateway;
import objects.User;
import presenter.*;
import use_case.addAccount.AccountDataAccessInterface;
import use_case.addAccount.AddAccountInteractor;
import use_case.addAccount.AddAccountOutputBoundary;
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

/**
 * Main JFrame of the application, serving as the primary window.
 * This frame manages the various panels of the application, such as sign-in, sign-up, home, and other functional panels.
 * It is responsible for initializing and orchestrating the navigation between different views based on user actions.
 */
public class MainFrame extends JFrame {
    private SignupUserDataAccessInterface userDataAccess = new DataAccessObject();
    private SendMoneyUserDataAccessInterface sendMoneyUserDataAccess = new DataAccessObject();
    private LoginUserDataAccessInterface loginUserDataAccess = new DataAccessObject();
    private SignupPresenter signupPresenter;
    private SendMoneyOutputBoundary sendMoneyOutputBoundary;
    private receiveMoneyInteractor receiveMoneyInteractor;
    private BankAccountPresenter bankAccountPresenter;
    private receiveMoneyDataAccessInterface receiveMoneyDataAccess = new DataAccessObject();
    private AccountDataAccessInterface accountDataAccess = new DataAccessObject();
    private final DataAccessObject dataAccess = new DataAccessObject();
    private final CurrencyConversionGateway currencyConversionGateway = new PolygonCurrencyConversionGateway();
    private final EmailSenderGateway emailSenderGateway = new EmailSender();

    /**
     * Constructs the MainFrame, setting up the application window, default settings,
     * and initializing the first panel (usually the initial panel).
     */
    public MainFrame() {
        setTitle("Globe Wallet");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        switchToPanel(new InitPanel(this));
        setVisible(true);
    }

    /**
     * Switches the current panel displayed in the MainFrame to the specified panel.
     *
     * @param panel The JPanel to be displayed in the MainFrame.
     */
    public void switchToPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }

    // Methods for switching to specific panels
    public void switchToSignupView() {
        SignupView signupView = new SignupView(this);
        SignupPresenter signupPresenter = new SignupPresenter(signupView, this, userDataAccess);
        signupView.setPresenter(signupPresenter);
        SignupInteractor signupInteractor = new SignupInteractor(userDataAccess, signupPresenter, emailSenderGateway);
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
        SendMoneyInteractor sendMoneyInteractor = new SendMoneyInteractor(sendMoneyUserDataAccess, sendMoneyPresenter, currencyConversionGateway, emailSenderGateway);
        sendMoneyPresenter.setSendMoneyInteractor(sendMoneyInteractor);
        moneyTransferPanel.setPresenter(sendMoneyPresenter);
        switchToPanel(moneyTransferPanel);
    }

    public void switchToReceiveMoneyPanel() {
        ReceiveMoneyPanel receiveMoneyPanel = new ReceiveMoneyPanel(this);
        ReceiveMoneyPresenter receiveMoneyPresenter = new ReceiveMoneyPresenter(receiveMoneyPanel);
        receiveMoneyInteractor receivemoneyInteractor = new receiveMoneyInteractor(receiveMoneyDataAccess, receiveMoneyPresenter);
        receiveMoneyPresenter.setReceiveMoneyInteractor(receivemoneyInteractor);
        receiveMoneyPanel.setPresenter(receiveMoneyPresenter);

        switchToPanel(receiveMoneyPanel);
    }

//    public void switchToBankAccountsPanel() {
//        BankAccountsPanel bankAccountsPanel = new BankAccountsPanel(this);
//        AddAccountOutputBoundary addAccountOutputBoundary = bankAccountPresenter;
//        AddAccountInteractor addAccountInteractor = new AddAccountInteractor(dataAccess, addAccountOutputBoundary);
//
//        bankAccountsPanel.setPresenter(bankAccountPresenter);
//        switchToPanel(bankAccountsPanel);
//    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}

