package interface_adapter.presenter;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import use_case.sendmoneytransfer.*;
import views.MainFrame;
import views.MoneyTransferPanel;

import javax.swing.*;

/**
 * Presenter responsible for handling the send money functionality in the application.
 * <p>
 * This class communicates with the send money interactor to execute money transfer operations
 * and updates the MoneyTransferPanel view based on the results of these operations.
 * </p>
 */
public class SendMoneyPresenter implements SendMoneyOutputBoundary {
    private MoneyTransferPanel moneyTransferView;
    private MainFrame frame;
    private SendMoneyInputBoundary sendMoneyInteractor;

    /**
     * Constructs a SendMoneyPresenter with the specified view and main application frame.
     *
     * @param view  The MoneyTransferPanel that this presenter is responsible for.
     * @param frame The main application window.
     */
    public SendMoneyPresenter(MoneyTransferPanel view, MainFrame frame) {
        this.moneyTransferView = view;
    }

    /**
     * Initiates a money transfer operation.
     *
     * @param userAccountId      The account ID of the user sending money.
     * @param receiverAccountId  The account ID of the receiver.
     * @param amount             The amount of money to send.
     * @param securityCode       The security code to authorize the transaction.
     */
    public void performTransfer(int userAccountId, int receiverAccountId, double amount, int securityCode) {
        SendMoneyInputData inputData = new SendMoneyInputData(userAccountId, receiverAccountId, amount, securityCode);
        sendMoneyInteractor.transfer(inputData);
    }

    /**
     * Checks if the accounts involved in the transaction are valid.
     *
     * @param senderId   The account ID of the sender.
     * @param receiverId The account ID of the receiver.
     */
    public void checkAccount(int senderId, int receiverId) {
        SendMoneyInputData inputData = new SendMoneyInputData(senderId, receiverId, 0, 0);
        sendMoneyInteractor.checkAccount(inputData);
    }

    /**
     * Performs a currency conversion for the transfer amount.
     *
     * @param senderId   The account ID of the sender.
     * @param receiverId The account ID of the receiver.
     * @param amount     The amount of money to convert.
     */
    public void performConversion(int senderId, int receiverId, double amount) {
        SendMoneyInputData inputData = new SendMoneyInputData(senderId, receiverId, amount, null);
        sendMoneyInteractor.convert(inputData);
    }

    /**
     * Displays a success message for account balance check in the view.
     *
     * @param outputData The data containing information about the successful account check.
     */
    @Override
    public void prepareSuccessCheckBalance(SendMoneyOutputData outputData) {
        SwingUtilities.invokeLater(() -> {
            String message = "Account Check Successful:\n" +
                    "Sender's Currency: " + outputData.getSenderCurrencyType() + "\n" +
                    "Receiver's Currency: " + outputData.getReceiverCurrencyType();
            JOptionPane.showMessageDialog(moneyTransferView, message, "Check Balance Successful", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    /**
     * Displays a success message for currency conversion in the view.
     *
     * @param outputData The data containing information about the successful conversion.
     */
    @Override
    public void prepareSuccessConvert(SendMoneyOutputData outputData) {
        SwingUtilities.invokeLater(() -> {
            String message = "Conversion successful:\n" +
                    "Amount to Send: " + outputData.getSendSendsAmount() + " " + outputData.getSenderCurrencyType() + "\n" +
                    "Amount Receiver Will Get: " + outputData.getReceiverReceivesAmount() + " " + outputData.getReceiverCurrencyType();
            JOptionPane.showMessageDialog(moneyTransferView, message, "Conversion Successful", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    /**
     * Displays a success message for the money transfer in the view.
     *
     * @param outputData The data containing information about the successful transfer.
     */
    @Override
    public void prepareSuccessTransfer(SendMoneyOutputData outputData) {
        SwingUtilities.invokeLater(() -> {
            String message = String.format("Transfer successful:\nTransaction ID: %d\nAmount Sent: %.2f %s\nAmount Received: %.2f %s",
                    outputData.getTransactionId(),
                    outputData.getSendSendsAmount(),
                    outputData.getSenderCurrencyType(),
                    outputData.getReceiverReceivesAmount(),
                    outputData.getReceiverCurrencyType());
            JOptionPane.showMessageDialog(moneyTransferView, message, "Transfer Successful", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    /**
     * Displays an error message in the view.
     *
     * @param errorMessage The error message to display.
     */
    @Override
    public void prepareFailView(String errorMessage) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(moneyTransferView, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        });
    }

    /**
     * Sets the SendMoneyInteractor for this presenter.
     *
     * @param sendMoneyInteractor The SendMoneyInteractor to be used by this presenter.
     */
    public void setSendMoneyInteractor(SendMoneyInteractor sendMoneyInteractor) {
        this.sendMoneyInteractor = sendMoneyInteractor;
    }
}
