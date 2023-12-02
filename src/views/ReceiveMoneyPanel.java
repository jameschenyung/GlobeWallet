package views;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;

public class ReceiveMoneyPanel extends JPanel {
    private JTextField transactionIdField, securityCodeField;
    private JButton checkTransactionButton, confirmButton, backButton;
    private MainFrame frame;

    public ReceiveMoneyPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title
        JLabel titleLabel = new JLabel("Receive Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(titleLabel, gbc);

        // Transaction ID Input
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Transaction ID:"), gbc);

        gbc.gridx = 1;
        transactionIdField = new JTextField(15);
        add(transactionIdField, gbc);

        // Check Transaction Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        checkTransactionButton = new JButton("Check Transaction");
        checkTransactionButton.addActionListener(e -> {
            // TODO: Add logic to check transaction
        });
        add(checkTransactionButton, gbc);

        // Security Code Input
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(new JLabel("Security Code:"), gbc);

        gbc.gridx = 1;
        securityCodeField = new JTextField(6);
        add(securityCodeField, gbc);

        // Confirm Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            // TODO: Add logic to confirm transaction
        });
        add(confirmButton, gbc);

        // Back Button
        gbc.gridy = 5;
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.switchToPanel(new HomePanel(frame))
        });
        add(backButton, gbc);
    }
}
