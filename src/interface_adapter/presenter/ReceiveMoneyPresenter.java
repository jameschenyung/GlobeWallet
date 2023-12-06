package interface_adapter.presenter;

import javax.swing.*;

import use_case.receiveMoney.receiveMoneyInteractor;
import use_case.receiveMoney.receiveMoneyOutputBoundary;
import views.ReceiveMoneyPanel;
import use_case.receiveMoney.*;
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
     */
    public ReceiveMoneyPresenter(ReceiveMoneyPanel view) {
        this.view = view;
    }


    /**
     * Sets the presenter for bank account operations.
     *
     * @param presenter The BankAccountPresenter to be associated with this presenter.
     */
    public void setPresenter(BankAccountPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Sets the interactor for receiving money operations.
     *
     * @param receivemoneyInteractor The receiveMoneyInteractor to handle the business logic.
     */
    public void setReceiveMoneyInteractor(receiveMoneyInteractor receivemoneyInteractor) {
        this.interactor = receivemoneyInteractor;
    }

    /**
     * Initiates a check for a transaction based on the given transaction ID.
     *
     * @param transactionId The ID of the transaction to be checked.
     */
    public void checkTransaction(Integer transactionId) {
        try {
            receiveMoneyInputData inputData = new receiveMoneyInputData(transactionId, null);
            inputData.setTransactionId(transactionId);
            interactor.verifyTransaction(inputData);
        } catch (Exception ex) {
            presentError("Error checking transaction: " + ex.getMessage());
        }
    }

    /**
     * Confirms the security code for a transaction.
     *
     * @param transactionId The ID of the transaction.
     * @param securityCode  The security code to confirm the transaction.
     */
    public void confirmSecurityCode(Integer transactionId, Integer securityCode) {
        try {
            receiveMoneyInputData inputData = new receiveMoneyInputData(transactionId, securityCode);
            inputData.setSecurityCode(securityCode);
            interactor.confirmSecurityCode(inputData);
        } catch (Exception ex) {
            presentError("Error confirming security code: " + ex.getMessage());
        }
    }


    // Overridden methods from receiveMoneyOutputBoundary

    @Override
    public void presentTransactionDetails(receiveMoneyOutputData outputData) {
        SwingUtilities.invokeLater(() -> {
            // Format the transaction details message
            String message = String.format("From %s: %.2f %s", outputData.getSenderName(),
                    outputData.getAmountReceived(), outputData.getReceiverCurrencyType());

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
