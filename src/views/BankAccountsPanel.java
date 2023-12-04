package views;

import presenter.BankAccountPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A JPanel representing the bank accounts management section of the application.
 * This panel provides functionalities to add new bank accounts and navigate back to the account panel.
 * It is connected with a BankAccountPresenter to handle the logic for bank account operations.
 */
public class BankAccountsPanel extends JPanel {
    private MainFrame frame;
    private BankAccountPresenter bankAccountPresenter;

    /**
     * Constructs a BankAccountsPanel associated with the given MainFrame.
     * Initializes the panel with buttons for adding new accounts and navigating back.
     *
     * @param frame The MainFrame that this panel is a part of.
     */
    public BankAccountsPanel(MainFrame frame) {
        this.frame = frame;
        JButton addAccountButton = new JButton("Add Account");
        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> frame.switchToPanel(new AccountPanel(frame)));
        addAccountButton.addActionListener(e -> openAddAccountPopup());

        this.add(backButton);
        this.add(addAccountButton);
    }

    /**
     * Sets the BankAccountPresenter for this panel.
     *
     * @param presenter The BankAccountPresenter to handle bank account actions and logic.
     */
    public void setPresenter(BankAccountPresenter presenter) {
        this.bankAccountPresenter = presenter;
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
