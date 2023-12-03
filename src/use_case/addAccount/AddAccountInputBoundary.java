package use_case.addAccount;
/**
 * Interface defining the contract for an input boundary in the "Add Account" use case.
 * This interface should be implemented by any class that serves as an interactor for
 * adding accounts in the system. It defines a method for processing the input data
 * necessary to add a new account.
 */

public interface AddAccountInputBoundary {
    /**
     * Processes the request to add a new account.
     * Implementers should handle the logic of adding an account based on the provided input data.
     *
     * @param inputData The input data required to add a new account, encapsulated in an AddAccountInputData object.
     */
    void addAccount(AddAccountInputData inputData);
}

