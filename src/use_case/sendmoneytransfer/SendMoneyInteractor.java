package use_case.sendmoneytransfer;

// import dataaccess.SendMoneyUserDataAccessInterface;
// import objects.Transaction;
// import presentation.SendMoneyOutputBoundary;

import java.util.UUID; // For generating unique transaction IDs

public class SendMoneyInteractor implements SendMoneyInputBoundary {
    private final SendMoneyUserDataAccessInterface userDataAccess;
    private final SendMoneyOutputBoundary outputBoundary;

    public SendMoneyInteractor(SendMoneyUserDataAccessInterface userDataAccess, SendMoneyOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void sendMoney(SendMoneyInputData inputData) {
        // Generate a unique transaction ID
        String transactionId = UUID.randomUUID().toString();

        Transaction transaction = new Transaction(transactionId, inputData.getSenderId(), inputData.getReceiverId(), inputData.getSecurityCode());

        boolean isSuccessful = processTransaction(transaction, inputData.getAmount());

        if (isSuccessful) {
            outputBoundary.presentSendMoneyResponse(new SendMoneyOutputData(true, "Transaction successful", transaction.getTransectionId()));
        } else {
            outputBoundary.presentSendMoneyResponse(new SendMoneyOutputData(false, "Transaction failed", null));
        }
    }

    private boolean processTransaction(Transaction transaction, double amount) {
        // Verify the sender's account
        Account senderAccount = userDataAccess.findAccountById(transaction.getSenderId());
        if (senderAccount == null || isAccountValid(senderAccount)) {
            return false; // Sender's account not found or invalid
        }
        // Check if the sender has sufficient balance
        if (senderAccount.getBalance() < amount) {
            return false; // Insufficient funds
        }
        // Verify the receiver's account
        Account receiverAccount = userDataAccess.findAccountById(transaction.getReceiverId());
        if (receiverAccount == null || isAccountValid(receiverAccount)) {
            return false; // Receiver's account not found or invalid
        }
        // Update the sender's and receiver's account balances
        updateAccountBalances(senderAccount, receiverAccount, amount);
        // Log the transaction (you would typically save the transaction to a database)
        userDataAccess.saveTransaction(transaction);

        return true; // Transaction processed successfully
    }

    private boolean isAccountValid(Account account) {
        // Placeholder for account validation logic
        return false;
    }

    private void updateAccountBalances(Account sender, Account receiver, double amount) {
        // Deduct the amount from the sender's account
        double newSenderBalance = sender.getBalance() - amount;
        userDataAccess.updateAccountBalance(sender.getAccountId(), newSenderBalance);

        // Add the amount to the receiver's account
        double newReceiverBalance = receiver.getBalance() + amount;
        userDataAccess.updateAccountBalance(receiver.getAccountId(), newReceiverBalance);
    }

    @Override
    public void login(SendMoneyInputData sendMoneyInputData) {

    }
}
