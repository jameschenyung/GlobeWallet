package use_case.sendmoneytransfer;

/**
 * Interface defining the contract for an output boundary in the send money use case.
 * This interface should be implemented by any class (typically a presenter) that handles
 * the presentation logic for displaying the results of a money sending operation. It defines methods
 * for presenting successful outcomes for different stages of the transaction as well as for handling failures.
 */
public interface SendMoneyOutputBoundary {
    /**
     * Prepares and presents the view for a successful check of account balances involved in the transaction.
     * Implementing classes should handle the logic to display a success message and relevant details.
     *
     * @param outputData The output data containing details of the successful account check.
     */
    void prepareSuccessCheckBalance(SendMoneyOutputData outputData);

    /**
     * Prepares and presents the view for a successful currency conversion in the transaction.
     * Implementing classes should handle the logic to display a success message and conversion details.
     *
     * @param outputData The output data containing details of the successful currency conversion.
     */
    void prepareSuccessConvert(SendMoneyOutputData outputData);

    /**
     * Prepares and presents the view for a successful money transfer.
     * Implementing classes should handle the logic to display a success message and transaction details.
     *
     * @param outputData The output data containing details of the successful transfer.
     */
    void prepareSuccessTransfer(SendMoneyOutputData outputData);

    /**
     * Presents an error message for a failed operation in the send money process.
     * Implementing classes should handle the logic to display or communicate the error message.
     *
     * @param message The error message to be presented for the failure.
     */
    void prepareFailView(String message);
}