package use_case.receiveMoney;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import objects.Account;
import objects.Transaction;
import use_case.sendmoneytransfer.SendMoneyOutputData;

import java.sql.SQLException;

public class receiveMoneyInteractor implements receiveMoneyInputBoundary {
    private final receiveMoneyDataAccessInterface dataAccess;
    private final receiveMoneyOutputBoundary outputBoundary;

    public receiveMoneyInteractor(receiveMoneyDataAccessInterface dataAccess, receiveMoneyOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void verifyTransaction(Integer transactionId) {
        try {
            Integer currentUserId = dataAccess.getCurrentUserId();
            Transaction transaction = dataAccess.getTransactionDetails(transactionId);

            if (transaction != null && transaction.getReceiverId().equals(currentUserId)) {
                String senderName = dataAccess.getFullName(transaction.getSenderId());
                String currency = dataAccess.getCurrencyByAccount(currentUserId);
                outputBoundary.presentTransactionDetails(senderName, transaction.getAmount(), currency);
            } else {
                outputBoundary.presentError("Transaction not found or you are not the receiver.");
            }
        } catch (SQLException e) {
            outputBoundary.presentError("Database error: " + e.getMessage());
        }
    }


    @Override
    public void confirmSecurityCode(Integer transactionId, Integer securityCode) {
        try {
            Integer currentUserId = dataAccess.getCurrentUserId();
            if (dataAccess.validateSecurityCode(securityCode, transactionId)) {
                Transaction transaction = dataAccess.getTransactionDetails(transactionId);
                String currency = dataAccess.getCurrencyByAccount(currentUserId);

                // Assume getNewBalance method calculates the new balance after the transaction
                Double newBalance = dataAccess.getNewBalance(transaction.getReceiverId(), transaction.getAmount());
                dataAccess.updateAccountBalance(transaction.getReceiverId(), newBalance);

                outputBoundary.presentTransactionConfirmation(transaction.getAmount(), currency, newBalance);
            } else {
                outputBoundary.presentError("Invalid security code.");
            }
        } catch (SQLException e) {
            outputBoundary.presentError("Database error: " + e.getMessage());
        }
    }

}


