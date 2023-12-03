package use_case.receiveMoney;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import objects.Account;
import objects.Transaction;
import use_case.sendmoneytransfer.SendMoneyOutputData;

import java.sql.SQLException;

/**
 * The interactor for handling receive money transactions.
 * Contains the business logic to process money receiving operations.
 */
public class receiveMoneyInteractor implements receiveMoneyInputBoundary {
    private final receiveMoneyDataAccessInterface dataAccess;
    private final receiveMoneyOutputBoundary outputBoundary;

    /**
     * Constructs a receiveMoneyInteractor.
     *
     * @param dataAccess    The data access interface for transaction data.
     * @param outputBoundary The output boundary for sending results back to the presenter.
     */
    public receiveMoneyInteractor(receiveMoneyDataAccessInterface dataAccess, receiveMoneyOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void verifyTransaction(receiveMoneyInputData inputData) {
        try {
            Integer currentUserId = dataAccess.getCurrentUserId();
            Transaction transaction = dataAccess.getTransactionDetails(inputData.getTransactionId());

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
    public void confirmSecurityCode(receiveMoneyInputData inputData) {
        try {
            Integer currentUserId = dataAccess.getCurrentUserId();
            if (dataAccess.validateSecurityCode(inputData.getSecurityCode(), inputData.getTransactionId())) {
                Transaction transaction = dataAccess.getTransactionDetails(inputData.getTransactionId());
                String currency = dataAccess.getCurrencyByAccount(currentUserId);

                Double newBalance = dataAccess.getAccountBalance(currentUserId) + transaction.getAmount();
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


