package views;

import presenter.SendMoneyPresenter;
import use_case.sendmoneytransfer.SendMoneyInputData;
import use_case.sendmoneytransfer.SendMoneyInteractor;

import javax.swing.*;
import java.awt.*;

public class MoneyTransferPanel extends JPanel {
    private JTextField userAccountNumberField, receiverAccountNumberField, amountField, securityCodeField;
    private JButton checkAccountsButton, checkAmountButton, transferButton;
    private SendMoneyPresenter presenter;
    private SendMoneyInteractor interactor;

    public MoneyTransferPanel(MainFrame frame, SendMoneyPresenter presenter) {
        this.presenter = presenter;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        userAccountNumberField = new JTextField(15);
        receiverAccountNumberField = new JTextField(15);
        amountField = new JTextField(10);

        add(new JLabel("Your Account Number:"), gbc);
        add(userAccountNumberField, gbc);

        add(new JLabel("Receiver's Account Number:"), gbc);
        add(receiverAccountNumberField, gbc);

        checkAccountsButton = new JButton("Check Accounts");
        checkAccountsButton.addActionListener(e -> {
            try {
                int userAccountId = Integer.parseInt(userAccountNumberField.getText());
                int receiverAccountId = Integer.parseInt(receiverAccountNumberField.getText());
                presenter.checkAccount(userAccountId, receiverAccountId);
            } catch (NumberFormatException ex) {
                showError("Invalid account number format.");
            }
        });
        add(checkAccountsButton, gbc);

        add(new JLabel("Amount Receiver Gets:"), gbc);
        add(amountField, gbc);

        checkAmountButton = new JButton("Check Amount");
        checkAmountButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                int userAccountId = Integer.parseInt(userAccountNumberField.getText());
                int receiverAccountId = Integer.parseInt(receiverAccountNumberField.getText());
                presenter.performConversion(userAccountId, receiverAccountId, amount);
            } catch (NumberFormatException ex) {
                showError("Invalid input format.");
            }
        });
        add(checkAmountButton, gbc);

        add(new JLabel("Security Code:"), gbc);
        securityCodeField = new JTextField(6);
        add(securityCodeField, gbc);

        transferButton = new JButton("Transfer");
        transferButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                int userAccountId = Integer.parseInt(userAccountNumberField.getText());
                int receiverAccountId = Integer.parseInt(receiverAccountNumberField.getText());
                int securityCode = Integer.parseInt(securityCodeField.getText());
                interactor.transfer(new SendMoneyInputData(userAccountId, receiverAccountId, amount, securityCode));
            } catch (NumberFormatException ex) {
                showError("Invalid input format.");
            }
        });

        add(transferButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchToPanel(new HomePanel(frame)));
        add(backButton, gbc);
    }

    public void setPresenter(SendMoneyPresenter presenter) {
        this.presenter = presenter;
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Transfer Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Transfer Error", JOptionPane.ERROR_MESSAGE);
    }

}
