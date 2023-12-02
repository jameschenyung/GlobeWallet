package views;

import use_case.addAccount.AddAccountInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAccountsPanel extends JPanel {
    private MainFrame frame;

    public BankAccountsPanel(MainFrame frame) {
        this.frame = frame;
        JButton addAccountButton = new JButton("Add Account");
        addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddAccountPopup();
            }
        });
        this.add(addAccountButton);
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

        // User ID Field
        dialog.add(new JLabel("User ID:"), constraints);
        constraints.gridx++;
        JTextField userIdField = new JTextField(10);
        dialog.add(userIdField, constraints);

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
                    int userId = Integer.parseInt(userIdField.getText());

                    AddAccountInputData inputData = new AddAccountInputData(accountNumber, currencyType, userId);
                    // Process inputData and handle output data

                    dialog.dispose();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for account number and user ID.");
                }
            }
        });
        dialog.add(addButton, constraints);

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
}
