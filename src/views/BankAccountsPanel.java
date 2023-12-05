package views;

import database.DataAccessObject;
import database.addAccountDataAccessObject;
import presenter.BankAccountPresenter;
import objects.Account;
import use_case.addAccount.AccountDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BankAccountsPanel extends JPanel {
    private MainFrame frame;
    private BankAccountPresenter bankAccountPresenter;
    private AccountDataAccessInterface dataAccess;

    public BankAccountsPanel(MainFrame frame) {
        this.frame = frame;
        this.dataAccess = new addAccountDataAccessObject();

        JButton addAccountButton = new JButton("Add Account");
        addAccountButton.addActionListener(e -> openAddAccountPopup());
        this.add(addAccountButton);

        JButton checkAccountsButton = new JButton("Check Accounts");
        checkAccountsButton.addActionListener(e -> displayAccounts());
        this.add(checkAccountsButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchToPanel(new AccountPanel(frame)));
        this.add(backButton);
    }

    private void displayAccounts() {
        ArrayList<Integer> accountIds = bankAccountPresenter.getAccountIds();
        for (Integer accountId : accountIds) {
            JPanel accountPanel = new JPanel();
            accountPanel.add(new JLabel("Account ID: " + accountId));

            JButton checkButton = new JButton("Check Details");
            checkButton.addActionListener(e -> bankAccountPresenter.checkAccountDetails(accountId));
            accountPanel.add(checkButton);

            this.add(accountPanel);
        }
        this.revalidate();
        this.repaint();
    }

    public void setPresenter(BankAccountPresenter presenter) {
        this.bankAccountPresenter = presenter;
    }

    public void displayAccountDetails(Account accountDetails) {
        JOptionPane.showMessageDialog(this,
                "Account ID: " + accountDetails.getAccountId() +
                        "\nBalance: " + dataAccess.getAccountBalance(accountDetails.getAccountId()) +
                        "\nCurrency: " + accountDetails.getCurrencyType(),
                "Account Details",
                JOptionPane.INFORMATION_MESSAGE);
    }


    private void openAddAccountPopup() {
        JDialog dialog = new JDialog(frame, "Add Account", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;

        // Account Number Field
        dialog.add(new JLabel("Account Number:"), constraints);
        constraints.gridx++;
        JTextField accountNumberField = new JTextField(10);
        dialog.add(accountNumberField, constraints);
        constraints.gridx = 0;
        constraints.gridy++;

        // Currency Type Field
        dialog.add(new JLabel("Currency Type:"), constraints);
        constraints.gridx++;
        JTextField currencyTypeField = new JTextField(10);
        dialog.add(currencyTypeField, constraints);
        constraints.gridx = 0;
        constraints.gridy++;

        // Add Account Button
        JButton addButton = new JButton("Add");
        constraints.gridwidth = 2;
        addButton.addActionListener(e -> {
            try {
                int accountNumber = Integer.parseInt(accountNumberField.getText());
                String currencyType = currencyTypeField.getText();
                bankAccountPresenter.addAccount(accountNumber, currencyType);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for account number and currency type.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(addButton, constraints);

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    /**
     * Displays a success message to the user.
     *
     * @param message The success message to be displayed.
     */
    public void showSuccess(String message) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE));
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE));
    }
}
