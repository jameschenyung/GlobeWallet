package presenter;

import javax.swing.*;

import use_case.receiveMoney.receiveMoneyInteractor;
import use_case.receiveMoney.receiveMoneyOutputBoundary;
import views.ReceiveMoneyPanel;

public class ReceiveMoneyPresenter implements receiveMoneyOutputBoundary {
    private final ReceiveMoneyPanel view;
    private receiveMoneyInteractor interactor;

    public ReceiveMoneyPresenter(ReceiveMoneyPanel view, receiveMoneyInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    public void checkTransaction(Integer transactionId) {
        interactor.verifyTransaction(transactionId);
    }

    public void confirmSecurityCode(Integer transactionId, Integer securityCode) {
        interactor.confirmSecurityCode(transactionId, securityCode);
    }

    @Override
    public void presentTransactionDetails(String senderName, Double amount, String currency) {
        SwingUtilities.invokeLater(() -> {
            // Format the transaction details message
            String message = String.format("From %s: %.2f %s", senderName, amount, currency);

            // Display the transaction details in a dialog
            JOptionPane.showMessageDialog(view, message, "Transaction Details", JOptionPane.INFORMATION_MESSAGE);
        });

    }

    @Override
    public void presentTransactionConfirmation(Double amount, String currency, Double updatedBalance) {
        SwingUtilities.invokeLater(() -> {
            // First confirmation message
            JOptionPane.showMessageDialog(view, "Transaction received", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Second message with details
            String detailsMessage = String.format("Amount Received: %.2f %s\nUpdated Balance: %.2f", amount, currency, updatedBalance);
            JOptionPane.showMessageDialog(view, detailsMessage, "Transaction Details", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    @Override
    public void presentError(String message) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
        });
    }
}
