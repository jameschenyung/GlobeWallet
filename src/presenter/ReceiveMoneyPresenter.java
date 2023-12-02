package presenter;

import use_case.receiveMoney.receiveMoneyOutputBoundary;
import views.ReceiveMoneyPanel;

import javax.swing.*;

public class ReceiveMoneyPresenter implements receiveMoneyOutputBoundary {
    private ReceiveMoneyPanel view;

    // ... other methods ...

    @Override
    public void presentTransactionDetails(String senderName, Double amount, String currency) {

    }

    @Override
    public void presentTransactionConfirmation(Double amount, String currency, Double updatedBalance) {
        SwingUtilities.invokeLater(() -> {
            // First pop-up
            JOptionPane.showMessageDialog(view, "Transaction received", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Second pop-up with details
            String detailsMessage = "Amount Received: " + amount + " " + currency +
                    "\nUpdated Balance: " + updatedBalance;
            JOptionPane.showMessageDialog(view, detailsMessage, "Transaction Details", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    @Override
    public void presentError(String message) {

    }

    // ... rest of the methods ...
}
