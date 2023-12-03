package use_case.addAccount;

import database.DataAccessObject;
/**
 * Interactor class for the "Add Account" use case.
 * This class handles the business logic of adding a new account. It interacts with the data access layer
 * to check the validity of the account and save it, and communicates with the output boundary to present results.
 */
public class AddAccountInteractor implements AddAccountInputBoundary {
    private AccountDataAccessInterface dataAccess;
    private AddAccountOutputBoundary outputBoundary;

    /**
     * Constructs an AddAccountInteractor with the specified data access object and output boundary.
     *
     * @param dataAccess     The data access object to interact with the database.
     * @param outputBoundary The output boundary to present the results of account addition.
     */
    public AddAccountInteractor(DataAccessObject dataAccess, AddAccountOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Adds a new account based on the given input data.
     * It validates the account number, saves the account if valid, and presents the result through the output boundary.
     *
     * @param inputData The input data containing the account number and currency type for the new account.
     */
    @Override
    public void addAccount(AddAccountInputData inputData) {
        try {
            if (dataAccess.isValidAccount(inputData.getAccountNumber())) {
                dataAccess.saveAccount(inputData.getAccountNumber(),
                        dataAccess.getCurrentUserId(), dataAccess.generateBalance(), inputData.getCurrencyType());
            } else {
                outputBoundary.presentAddAccountResult(new AddAccountOutputData(false, "Account number taken."));
            }

            outputBoundary.presentAddAccountResult(new AddAccountOutputData(true, "Account successfully added"));
        } catch (Exception e) {
            outputBoundary.presentAddAccountResult(new AddAccountOutputData(false, e.getMessage()));
        }
    }
}