package presenter;

import objects.Account;
import use_case.addAccount.*;
import use_case.receiveMoney.receiveMoneyInteractor;
import views.BankAccountsPanel;

import java.util.ArrayList;

/**
 * Presenter for the bank accounts panel.
 * <p>
 * This presenter acts as an intermediary between the bank accounts view (panel) and the add account interactor (use case).
 * It handles user requests to add new bank accounts and processes the results of these requests.
 * </p>
 */
public class BankAccountPresenter implements AddAccountOutputBoundary {
    private BankAccountsPanel view;
    private AddAccountInteractor interactor;
    private AccountDataAccessInterface dataAccess;

    /**
     * Constructor for BankAccountPresenter.
     * <p>
     * Initializes the presenter with a view and an add account interactor.
     * </p>
     *
     * @param view The bank accounts panel that this presenter manages.
     */
    public BankAccountPresenter(BankAccountsPanel view, AccountDataAccessInterface accountDataAccessInterface) {
        this.view = view;
        this.interactor = new AddAccountInteractor(accountDataAccessInterface, this);
        this.dataAccess = accountDataAccessInterface; // Assign the data access interface
    }

    public void setAddAccountInteractor(AddAccountInteractor addAccountInteractor) {
        this.interactor = addAccountInteractor;
    }

    /**
     * Initiates the process of adding a new bank account.
     * <p>
     * This method is called when the user attempts to add a new account. It creates
     * the necessary input data and triggers the interactor to add the account.
     * </p>
     *
     * @param accountNumber The account number for the new account.
     * @param currencyType  The currency type for the new account.
     */
    public void addAccount(Integer accountNumber, String currencyType) {
        AddAccountInputData inputData = new AddAccountInputData(accountNumber, currencyType);
        interactor.addAccount(inputData);
    }

    public ArrayList<Integer> getAccountIds() {
        Integer currentUserId = interactor.getCurrentUserId();
        return dataAccess.getUserBankAccounts(currentUserId);
    }

    public void checkAccountDetails(Integer accountId) {
        try {
            Account accountDetails = dataAccess.getAccount(accountId.toString());
            if (accountDetails != null) {
                view.displayAccountDetails(accountDetails);
            } else {
                view.showError("No details found for account ID: " + accountId);
            }
        } catch (Exception e) {
            view.showError("Error fetching account details: " + e.getMessage());
        }
    }

    @Override
    public void presentAddAccountResult(AddAccountOutputData outputData) {
        if (outputData.isSuccess()) {
            view.showSuccess(outputData.getMessage());
        } else {
            view.showError(outputData.getMessage());
        }
    }
}