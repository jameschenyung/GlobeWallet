package presenter;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import use_case.sendmoneytransfer.*;
import views.MainFrame;
import views.MoneyTransferPanel;

import javax.swing.*;

public class SendMoneyPresenter implements SendMoneyOutputBoundary {
    private MoneyTransferPanel moneyTransferView;
    private MainFrame frame;
    private SendMoneyInputBoundary sendMoneyInteractor;

    public SendMoneyPresenter(MoneyTransferPanel view, MainFrame frame, SendMoneyInputBoundary interactor) {
        this.moneyTransferView = view;
        this.sendMoneyInteractor = interactor;
    }

    public void checkAccount(int senderId, int receiverId) {
        SendMoneyInputData inputData = new SendMoneyInputData(senderId, receiverId, 0, 0);
        sendMoneyInteractor.checkAccount(inputData);
    }

    public void performConversion(int senderId, int receiverId, double amount) {
        SendMoneyInputData inputData = new SendMoneyInputData(senderId, receiverId, amount, null);
        sendMoneyInteractor.convert(inputData);
    }

    @Override
    public void prepareSuccessCheckBalance(SendMoneyOutputData outputData) {
        SwingUtilities.invokeLater(() -> {
            String message = "Account Check Successful:\n" +
                    "Sender's Currency: " + outputData.getSenderCurrencyType() + "\n" +
                    "Receiver's Currency: " + outputData.getReceiverCurrencyType();
            JOptionPane.showMessageDialog(moneyTransferView, message, "Check Balance Successful", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    @Override
    public void prepareSuccessConvert(SendMoneyOutputData outputData) {
        SwingUtilities.invokeLater(() -> {
            String message = "Conversion successful:\n" +
                    "Amount to Send: " + outputData.getSendSendsAmount() + " " + outputData.getSenderCurrencyType() + "\n" +
                    "Amount Receiver Will Get: " + outputData.getReceiverReceivesAmount() + " " + outputData.getReceiverCurrencyType();
            JOptionPane.showMessageDialog(moneyTransferView, message, "Conversion Successful", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    @Override
    public void prepareSuccessTransfer(SendMoneyOutputData outputData) {
        SwingUtilities.invokeLater(() -> {
            String message = "Transfer successful:\n" +
                    "Amount Sent: " + outputData.getSendSendsAmount() + " " + outputData.getSenderCurrencyType() + "\n" +
                    "Amount Received: " + outputData.getReceiverReceivesAmount() + " " + outputData.getReceiverCurrencyType();
            JOptionPane.showMessageDialog(moneyTransferView, message, "Transfer Successful", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    @Override
    public void prepareFailView(String errorMessage) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(moneyTransferView, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        });
    }

    public void setSendMoneyInteractor(SendMoneyInteractor sendMoneyInteractor) {
        this.sendMoneyInteractor = sendMoneyInteractor;
    }
}