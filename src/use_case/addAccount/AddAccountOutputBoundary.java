package use_case.addAccount;
/**
 * Interface defining the contract for an output boundary in the "Add Account" use case.
 * This interface should be implemented by any class that serves as a presenter or an output processor
 * for displaying the results of adding an account. It defines a method for presenting the outcome
 * using the provided output data.
 */

public interface AddAccountOutputBoundary {
    /**
     * Presents the result of an attempt to add a new account.
     * Implementers should handle the presentation logic of displaying the outcome based on the provided output data.
     *
     * @param outputData The output data containing the result and relevant information of the account addition process.
     */
    void presentAddAccountResult(AddAccountOutputData outputData);
}
