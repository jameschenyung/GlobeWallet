package use_case.addAccount;

import database.DataAccessObject;

public class AddAccountInteractor implements AddAccountInputBoundary {
    private AccountDataAccessInterface dataAccess;
    private AddAccountOutputBoundary outputBoundary;

    public AddAccountInteractor(DataAccessObject dataAccess, AddAccountOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void addAccount(AddAccountInputData inputData) {
        try {
            if (dataAccess.isValidAccount(inputData.getAccountNumber())) {
                dataAccess.saveAccount(inputData.getAccountNumber(),
                        dataAccess.getCurrentUserId(), dataAccess.generateBalance(), inputData.getCurrencyType());
                outputBoundary.presentAddAccountResult(new AddAccountOutputData(true, "Account successfully added"));
            } else {
                outputBoundary.presentAddAccountResult(new AddAccountOutputData(false, "Account number taken."));
            }

        } catch (Exception e) {
            outputBoundary.presentAddAccountResult(new AddAccountOutputData(false, e.getMessage()));
        }
    }
}