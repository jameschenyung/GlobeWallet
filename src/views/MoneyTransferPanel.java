package views;

import javax.swing.*;
import java.awt.*;

public class MoneyTransferPanel extends JPanel {
    private JTextField userAccountNumberField, receiverAccountNumberField, amountField;
    private JLabel receiverCurrencyLabel, conversionResultLabel;
    private JButton checkUserAccountButton, checkAmountButton, transferButton, backButton;

    public MoneyTransferPanel(MainFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        userAccountNumberField = new JTextField(15);
        receiverAccountNumberField = new JTextField(15);
        amountField = new JTextField(10);
        receiverCurrencyLabel = new JLabel("Receiver's Currency: [Not Set]");
        conversionResultLabel = new JLabel("Conversion Result: [Not Calculated]");

        add(new JLabel("Your Account Number:"), gbc);
        add(userAccountNumberField, gbc);

        checkUserAccountButton = new JButton("Check Account");
        checkUserAccountButton.addActionListener(e -> {
            // Logic to fetch and display user account details
        });
        add(checkUserAccountButton, gbc);

        add(new JLabel("Receiver's Account Number:"), gbc);
        add(receiverAccountNumberField, gbc);
        receiverAccountNumberField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                // Logic to fetch and display receiver's currency
            }
        });
        add(receiverCurrencyLabel, gbc);

        add(new JLabel("Amount Receiver Gets:"), gbc);
        add(amountField, gbc);

        checkAmountButton = new JButton("Check Amount");
        checkAmountButton.addActionListener(e -> {
            // Logic to calculate and show conversion
        });
        add(checkAmountButton, gbc);
        add(conversionResultLabel, gbc);

        transferButton = new JButton("Transfer");
        transferButton.addActionListener(e -> {
            // Logic to confirm and perform the transfer
        });
        add(transferButton, gbc);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchToPanel(new HomePanel(frame)));
        add(backButton, gbc);
    }
}
