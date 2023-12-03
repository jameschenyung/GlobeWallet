package presenter;

import use_case.addAccount.*;
import views.BankAccountsPanel;

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

    /**
     * Constructor for BankAccountPresenter.
     * <p>
     * Initializes the presenter with a view and an add account interactor.
     * </p>
     *
     * @param view       The bank accounts panel that this presenter manages.
     */
    public BankAccountPresenter(BankAccountsPanel view, AccountDataAccessInterface accountDataAccessInterface) {
        this.view = view;
        this.interactor = new AddAccountInteractor(accountDataAccessInterface, this);
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

    public void setInteractor(AddAccountInteractor addAccountInteractor) {
        this.interactor = addAccountInteractor;
    }

    /**
     * Presents the result of an attempt to add a new bank account.
     * <p>
     * This method is invoked by the interactor to deliver the outcome of the add account operation.
     * Depending on the result, it will display either a success or error message.
     * </p>
     *
     * @param outputData The data containing the result of the add account operation.
     */
    @Override
    public void presentAddAccountResult(AddAccountOutputData outputData) {
        if (outputData.isSuccess()) {
            view.showSuccess(outputData.getMessage());
        } else {
            view.showError(outputData.getMessage());
        }
    }
}
