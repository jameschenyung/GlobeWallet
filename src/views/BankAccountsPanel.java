package views;

import presenter.BankAccountPresenter;
import presenter.SendMoneyPresenter;
import use_case.addAccount.AddAccountInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAccountsPanel extends JPanel {
    private MainFrame frame;
    private BankAccountPresenter bankAccountPresenter;

    public BankAccountsPanel(MainFrame frame, BankAccountPresenter bankAccountPresenter) {
        this.frame = frame;
        this.bankAccountPresenter = bankAccountPresenter;
        JButton addAccountButton = new JButton("Add Account");
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToPanel(new AccountPanel(frame));
            }
        });
        this.add(backButton);

        addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddAccountPopup();
            }
        });
        this.add(addAccountButton);
    }

    public void setPresenter(BankAccountPresenter presenter) {
        this.bankAccountPresenter = presenter;
    }

    private void openAddAccountPopup() {
        JDialog dialog = new JDialog(frame, "Add Account", true);
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

        // Reset for next row
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add Account Logic Here
                try {
                    int accountNumber = Integer.parseInt(accountNumberField.getText());
                    String currencyType = currencyTypeField.getText();

                    AddAccountInputData inputData = new AddAccountInputData(accountNumber, currencyType);
                    // Process inputData and handle output data

                    dialog.dispose();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for account number");
                }
            }
        });
        dialog.add(addButton, constraints);

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
