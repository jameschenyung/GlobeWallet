package use_case.addAccount;

import database.DataAccessObject;

public class AddAccountInteractor implements AddAccountInputBoundary {
    private AccountDataAccessInterface dataAccess;
    private AddAccountOutputBoundary outputBoundary;

    public AddAccountInteractor(AccountDataAccessInterface dataAccess, AddAccountOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    public Integer getCurrentUserId() {
        return dataAccess.getCurrentUserId();
    }

    @Override
    public void addAccount(AddAccountInputData inputData) {
        try {
            if (!dataAccess.isValidCurrency(inputData.getCurrencyType())) {
                outputBoundary.presentAddAccountResult(new AddAccountOutputData(false, "Invalid currency type."));
                return;
            }

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