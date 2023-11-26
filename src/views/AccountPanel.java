package views;

import presenter.MyDetailsPresenter;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {
    private MainFrame frame;
    private JLabel myAccountLabel;

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

        JButton detailsButton = new JButton("My Details");
        detailsButton.addActionListener(e -> showDetailsPanel());
        add(detailsButton, gbc);

        JButton bankAccountsButton = new JButton("Bank Accounts");
        bankAccountsButton.addActionListener(e -> bankAccountsPanel());
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

    private void showDetailsPanel() {
        frame.switchToPanel(new MyDetailsPanel(frame));
    }

    private void bankAccountsPanel() {
        frame.switchToPanel(new BankAccountsPanel(frame));
    }

    private void signOut() {
        int response = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to log out?",
                "Log Out",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            clearSessionData();
            frame.switchToPanel(new SignInPanel(frame));
        }
    }

    private void clearSessionData(){

    }
}
