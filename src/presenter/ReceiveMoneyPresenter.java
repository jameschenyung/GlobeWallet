package presenter;

import javax.swing.*;

import use_case.receiveMoney.receiveMoneyInteractor;
import use_case.receiveMoney.receiveMoneyOutputBoundary;
import views.ReceiveMoneyPanel;

/**
 * The presenter for the receive money feature.
 * Handles the presentation logic for receiving money transactions.
 */
public class ReceiveMoneyPresenter implements receiveMoneyOutputBoundary {
    private final ReceiveMoneyPanel view;
    private receiveMoneyInteractor interactor;
    private BankAccountPresenter presenter;

    /**
     * Constructs a ReceiveMoneyPresenter.
     *
     * @param view      The view associated with this presenter.
     * @param interactor The interactor used for processing business logic.
     */
    public ReceiveMoneyPresenter(ReceiveMoneyPanel view, receiveMoneyInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }


    public void setPresenter(BankAccountPresenter presenter) {
        this.presenter = presenter;
    }

    public void checkTransaction(Integer transactionId) {
        try {
            interactor.verifyTransaction(transactionId);
        } catch (Exception ex) {
            presentError("Error checking transaction: " + ex.getMessage());
        }
    }

    public void confirmSecurityCode(Integer transactionId, Integer securityCode) {
        try {
            interactor.confirmSecurityCode(transactionId, securityCode);
        } catch (Exception ex) {
            presentError("Error confirming security code: " + ex.getMessage());
        }
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
