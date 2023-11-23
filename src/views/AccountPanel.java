package views;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {
    private MainFrame frame;
    private JLabel myAccountLabel;

    public AccountPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton detailsButton = new JButton("My Details");
        detailsButton.addActionListener(e -> showDetailsPanel());
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(detailsButton, gbc);

        JButton bankAccountsButton = new JButton("Bank Acounts");
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

        // This extra component stretches to take all the space the buttons don't use
        gbc.weighty = 1; // Give row weight to push components to the top
        add(Box.createGlue(), gbc); // Invisible component

//        myAccountLabel = new JLabel("My Account");
//        myAccountLabel.setBounds(10, 20, 200, 25);
//        Font myAccFont = myAccountLabel.getFont();
//        float newSize = 18.0f;
//        myAccountLabel.setFont(myAccFont.deriveFont(newSize));
//        add(myAccountLabel);
//
//        JButton signOutButton = new JButton("Sign Out");
//        signOutButton.setBounds(10, 50, 100, 25);
//        signOutButton.addActionListener(e -> signOut());
//        add(signOutButton);
    }

    private void showDetailsPanel() {
        frame.switchToPanel(new MyDetailsPanel(frame));
    }

    private void bankAccountsPanel() {
        frame.switchToPanel(new BankAccountsPanel(frame));
    }

    private void signOut() {
        int response = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to sign out?",
                "Sign Out",
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
