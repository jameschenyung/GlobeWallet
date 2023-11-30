package use_case.addAccount;

import objects.Account;
import objects.User;
import database.DataAccessObject; // This should be the path to your DAO implementation

public class AddAccountInteractor implements AddAccountInputBoundary {
    private DataAccessObject dataAccess; // Direct reference to DAO instead of through an interface
    private AddAccountOutputBoundary outputBoundary;

    public AddAccountInteractor(DataAccessObject dataAccess, AddAccountOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void addAccount(AddAccountInputData inputData) {
        try {
            // Replace the findUserById method call with the direct DAO call
            User user = dataAccess.getUser(inputData.getUserId());
            if (user == null) {
                outputBoundary.presentAddAccountResult(new AddAccountOutputData(false, "User not found"));
                return;
            }

            // Create a new Account with the given details. Assuming the Account constructor matches your provided code.
            // Since Account constructor sets balance to 0.0, we don't need to pass it
            Account newAccount = new Account(null, user.getUserId(), 0.0, inputData.getCurrencyType());

            // Add the new account to the user's list of accounts
            user.addAccount(newAccount);
            String accountId = String.valueOf(newAccount.getAccountId());

            int userId = user.getUserId();

            dataAccess.saveAccount(accountId, userId, newAccount.getBalance());

            // Notify the output boundary of the result
            outputBoundary.presentAddAccountResult(new AddAccountOutputData(true, "Account successfully added"));
        } catch (Exception e) {
            outputBoundary.presentAddAccountResult(new AddAccountOutputData(false, e.getMessage()));
        }
    }
}
