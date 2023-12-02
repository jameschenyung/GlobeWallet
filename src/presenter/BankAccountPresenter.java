package presenter;

import use_case.addAccount.AddAccountInputData;
import use_case.addAccount.AddAccountInteractor;
import use_case.addAccount.AddAccountOutputBoundary;
import use_case.addAccount.AddAccountOutputData;
import views.BankAccountsPanel;

public class BankAccountPresenter implements AddAccountOutputBoundary {
    private BankAccountsPanel view;
    private AddAccountInteractor interactor;

    public void BankAccountsPresenter(BankAccountsPanel view, AddAccountInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void addAccount(int accountNumber, String currencyType) {
        AddAccountInputData inputData = new AddAccountInputData(accountNumber, currencyType);
        interactor.addAccount(inputData);
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
