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


    /**
     * Verifies the details of a transaction based on the given input data.
     * Checks if the transaction exists and if the current user is the intended receiver.
     *
     * @param inputData The input data containing transaction details to be verified.
     */
    @Override
    public void verifyTransaction(receiveMoneyInputData inputData) {
        Integer receiverId = dataAccess.getTransactionReceiverId(inputData.getTransactionId());

        if (dataAccess.hasTransaction(inputData.getTransactionId()) && dataAccess.accountUnderCurrentUser(receiverId)) {
            String senderName = dataAccess.getFullName(dataAccess.getUserIdbyAccountId(dataAccess.getTransactionSenderId(inputData.getTransactionId())));
            String currency = dataAccess.getCurrencyByAccount(receiverId);
            outputBoundary.presentTransactionDetails(new receiveMoneyOutputData(true, "Successful", senderName,
                    dataAccess.getTransactionReceiverId(inputData.getTransactionId()),currency, dataAccess.getTransactionAmount(inputData.getTransactionId())));
        } else {
            outputBoundary.presentError("Transaction not found or you are not the receiver.");
        }
    }


    /**
     * Confirms the security code for a transaction and processes the transaction if valid.
     * Updates the receiver's account balance and marks the transaction as received upon successful validation.
     *
     * @param inputData The input data containing the transaction ID and security code to be confirmed.
     */
    @Override
    public void confirmSecurityCode(receiveMoneyInputData inputData) {
        try {
            Integer receiverId = dataAccess.getTransactionReceiverId(inputData.getTransactionId());
            if (dataAccess.validateSecurityCode(inputData.getSecurityCode(), inputData.getTransactionId()) &&
            dataAccess.hasTransaction(inputData.getTransactionId()) && dataAccess.accountUnderCurrentUser(receiverId)) {
                String currency = dataAccess.getCurrencyByAccount(receiverId);

                Double newBalance = dataAccess.getAccountBalance(receiverId) + dataAccess.getTransactionAmount(inputData.getTransactionId());
                dataAccess.updateAccountBalance(receiverId, newBalance);
                dataAccess.transactionReceived(inputData.getTransactionId());

                outputBoundary.presentTransactionConfirmation(dataAccess.getTransactionAmount(inputData.getTransactionId()), currency, newBalance);
            } else {
                outputBoundary.presentError("Invalid security code.");
            }
        } catch (SQLException e) {
            outputBoundary.presentError("Database error: " + e.getMessage());
        }
    }

}


