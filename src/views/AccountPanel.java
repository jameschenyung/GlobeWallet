package views;

import database.UpdateUserDataAccessObject;
import database.addAccountDataAccessObject;
import interface_adapter.EmailSender.EmailSender;
import interface_adapter.EmailSender.EmailSenderGateway;
import interface_adapter.presenter.BankAccountPresenter;
import interface_adapter.presenter.UpdateUserPresenter;
import use_case.addAccount.AccountDataAccessInterface;
import use_case.updateUser.UpdateUserDataAccessInterface;


import javax.swing.*;
import java.awt.*;

/**
 * A JPanel representing the account section of the application.
 * This panel includes options for viewing account details, managing bank accounts,
 * logging out, and navigating back to the home panel. It serves as a central hub
 * for account-related interactions within the application.
 */
public class AccountPanel extends JPanel {
    private MainFrame frame;
    private JLabel myAccountLabel;
    private BankAccountPresenter bankAccountPresenter;
    private UpdateUserPresenter updateUserPresenter;
    private AccountDataAccessInterface accountDataAccessInterface = new addAccountDataAccessObject();
    private UpdateUserDataAccessInterface updateUserDataAccess = new UpdateUserDataAccessObject();
    private EmailSenderGateway emailSenderGateway = new EmailSender();

    /**
     * Constructs an AccountPanel associated with the given MainFrame.
     *
     * @param frame The MainFrame that this panel is a part of.
     */
    public AccountPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel myAccountLabel = new JLabel("MY ACCOUNT");
        myAccountLabel.setFont(new Font(myAccountLabel.getFont().getName(), Font.PLAIN, 18));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(myAccountLabel, gbc);

        gbc.gridy++;

        JButton detailsButton = new JButton("Update Profile");
        detailsButton.addActionListener(e -> switchToUserProfilePanel());
        add(detailsButton, gbc);

        JButton bankAccountsButton = new JButton("Bank Accounts");
        bankAccountsButton.addActionListener(e -> switchToBankAccountsPanel());
        gbc.gridy++;
        add(bankAccountsButton, gbc);

        JButton logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(e -> signOut());
        gbc.gridy++;
        add(logOutButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchToPanel(new HomePanel(frame)));
        gbc.gridy++;
        add(backButton, gbc);

        gbc.weighty = 1;
        add(Box.createGlue(), gbc);
    }

    private void switchToUserProfilePanel() {
        UserProfilePanel userProfilePanel = new UserProfilePanel(frame);
        updateUserPresenter = new UpdateUserPresenter(userProfilePanel, updateUserDataAccess, emailSenderGateway);
        userProfilePanel.setPresenter(updateUserPresenter);
        frame.switchToPanel(userProfilePanel);
    }

    private void switchToBankAccountsPanel() {
        BankAccountsPanel bankAccountsPanel = new BankAccountsPanel(frame);
        bankAccountPresenter = new BankAccountPresenter(bankAccountsPanel, accountDataAccessInterface);
        bankAccountsPanel.setPresenter(bankAccountPresenter);
        frame.switchToPanel(bankAccountsPanel);
    }

    private void signOut() {
        int response = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to log out?",
                "Log Out",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            clearSessionData();
            frame.switchToPanel(new InitPanel(frame));
        }
    }

    private void clearSessionData(){

    }

}
