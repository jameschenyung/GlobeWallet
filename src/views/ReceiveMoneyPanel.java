package views;

import javax.swing.*;
import java.awt.*;
import presenter.ReceiveMoneyPresenter;
import views.AccountPanel;
import views.MainFrame;

public class ReceiveMoneyPanel extends JPanel {
    private JTextField transactionIdField, securityCodeField;
    private JButton checkTransactionButton, confirmButton, backButton;
    private ReceiveMoneyPresenter presenter;
    private MainFrame frame;

    public ReceiveMoneyPanel(MainFrame frame, ReceiveMoneyPresenter presenter) {
        this.frame = frame;
        this.presenter = presenter;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Transaction ID Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Transaction ID:"), gbc);
        gbc.gridx = 1;
        transactionIdField = new JTextField(15);
        add(transactionIdField, gbc);

        // Security Code Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Security Code:"), gbc);
        gbc.gridx = 1;
        securityCodeField = new JTextField(15);
        add(securityCodeField, gbc);

        // Check Transaction Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        checkTransactionButton = new JButton("Check Transaction");
        checkTransactionButton.addActionListener(e -> {
            try {
                Integer transactionId = Integer.parseInt(transactionIdField.getText());
                presenter.checkTransaction(transactionId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid transaction ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(checkTransactionButton, gbc);


        // Confirm Button
        gbc.gridy = 3;
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            Integer transactionId = Integer.parseInt(transactionIdField.getText());
            Integer securityCode = Integer.parseInt(securityCodeField.getText());
            presenter.confirmSecurityCode(transactionId, securityCode);
        });
        add(confirmButton, gbc);

        // Back Button
        gbc.gridy = 4;
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.switchToPanel(new AccountPanel(frame));
        });
        add(backButton, gbc);
    }

    public void setPresenter(ReceiveMoneyPresenter presenter) {
        this.presenter = presenter;
    }
}
