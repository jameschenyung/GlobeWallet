package use_case.addAccount;

import objects.Account;
import objects.User;
import database.DataAccessObject;

public class AddAccountInteractor implements AddAccountInputBoundary {
    private DataAccessObject dataAccess;
    private AddAccountOutputBoundary outputBoundary;

    public AddAccountInteractor(DataAccessObject dataAccess, AddAccountOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void addAccount(AddAccountInputData inputData) {
        try {

            User user = dataAccess.getUser(inputData.getUserId());
            if (user == null) {
                outputBoundary.presentAddAccountResult(new AddAccountOutputData(false, "User not found"));
                return;
            }


            Account newAccount = new Account(null, user.getUserId(), 0.0, inputData.getCurrencyType());


            user.addAccount(newAccount);
            String accountId = String.valueOf(newAccount.getAccountId());

            int userId = user.getUserId();

            dataAccess.saveAccount(accountId, userId, newAccount.getBalance());

            outputBoundary.presentAddAccountResult(new AddAccountOutputData(true, "Account successfully added"));
        } catch (Exception e) {
            outputBoundary.presentAddAccountResult(new AddAccountOutputData(false, e.getMessage()));
        }
    }
}