package views;

import javax.swing.*;
import java.awt.*;
import presenter.ReceiveMoneyPresenter;
import views.AccountPanel;
import views.MainFrame;

/**
 * The panel for receiving money transactions.
 * This UI component allows users to check and confirm money transactions.
 */

public class ReceiveMoneyPanel extends JPanel {
    private JTextField transactionIdField, securityCodeField;
    private JButton checkTransactionButton, confirmButton, backButton;
    private ReceiveMoneyPresenter presenter;
    private MainFrame frame;

    /**
     * Constructs the ReceiveMoneyPanel.
     *
     * @param frame The main application frame.
     */
    public ReceiveMoneyPanel(MainFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;


        // Transaction ID Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Reset to default
        add(new JLabel("Transaction ID:"), gbc);

        // Transaction ID Field
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Give weight to expand
        transactionIdField = new JTextField(15);
        transactionIdField.setMinimumSize(transactionIdField.getPreferredSize());
        add(transactionIdField, gbc);

        // Reset fill and weightx
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;

        // Check Transaction Button
        gbc.gridx = 0;
        gbc.gridy = 1;
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

        // Reset gridwidth
        gbc.gridwidth = 1;

        // Security Code Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Security Code:"), gbc);

        // Security Code Field
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Allow field to expand
        securityCodeField = new JTextField(15);
        securityCodeField.setMinimumSize(securityCodeField.getPreferredSize());
        add(securityCodeField, gbc);

        // Reset fill and weightx for buttons
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;

        // Confirm Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            try {
                // Validate and parse inputs
                Integer transactionId = Integer.parseInt(transactionIdField.getText());
                Integer securityCode = Integer.parseInt(securityCodeField.getText());
                presenter.confirmSecurityCode(transactionId, securityCode);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Transaction ID and Security Code.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(confirmButton, gbc);

        // Back Button
        gbc.gridy = 4;
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.switchToPanel(new HomePanel(frame));
        });
        add(backButton, gbc);
    }
    /**
     * Sets the ReceiveMoneyPresenter for this panel.
     *
     * @param presenter The ReceiveMoneyPresenter to handle the actions and business logic.
     */
    public void setPresenter(ReceiveMoneyPresenter presenter) {
        this.presenter = presenter;
    }
}
