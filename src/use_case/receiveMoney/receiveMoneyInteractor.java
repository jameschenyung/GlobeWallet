package use_case.receiveMoney;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import objects.Account;
import objects.Transaction;
import use_case.sendmoneytransfer.SendMoneyOutputData;

import java.sql.SQLException;
import java.util.Objects;

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
        Integer currentUserId = dataAccess.getCurrentUserId();
        Integer receiverId = dataAccess.getTransactionReceiverId(inputData.getTransactionId());

        if (dataAccess.hasTransaction(inputData.getTransactionId()) && Objects.equals(currentUserId, receiverId)) {
            String senderName = dataAccess.getFullName(dataAccess.getTransactionSenderId(inputData.getTransactionId()));
            String currency = dataAccess.getCurrencyByAccount(currentUserId);
            outputBoundary.presentTransactionDetails(senderName, dataAccess.getTransactionAmount(inputData.getTransactionId()), currency);
        } else {
            outputBoundary.presentError("Transaction not found or you are not the receiver.");
        }
    }


    @Override
    public void confirmSecurityCode(receiveMoneyInputData inputData) {
        try {
            Integer currentUserId = dataAccess.getCurrentUserId();
            if (dataAccess.validateSecurityCode(inputData.getSecurityCode(), inputData.getTransactionId())) {
                String currency = dataAccess.getCurrencyByAccount(currentUserId);

                Double newBalance = dataAccess.getAccountBalance(currentUserId) + dataAccess.getTransactionAmount(currentUserId);
                dataAccess.updateAccountBalance(currentUserId, newBalance);

                outputBoundary.presentTransactionConfirmation(dataAccess.getTransactionAmount(currentUserId), currency, newBalance);
            } else {
                outputBoundary.presentError("Invalid security code.");
            }
        } catch (SQLException e) {
            outputBoundary.presentError("Database error: " + e.getMessage());
        }
    }

}


