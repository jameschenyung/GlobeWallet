package use_case.receiveMoney;
/**
 * Interface defining the contract for an output boundary in the receive money use case.
 * This interface should be implemented by any class (typically a presenter) that handles
 * the presentation logic for displaying the results of a money receiving operation. It defines methods
 * for presenting transaction details, confirmation, and error messages.
 */
import use_case.sendmoneytransfer.SendMoneyOutputData;


public interface receiveMoneyOutputBoundary {

    /**
     * Presents the details of a transaction to the user.
     * Implementing classes should handle the logic to display transaction details based on the provided output data.
     *
     * @param outputData The output data containing details of the transaction.
     */
    void presentTransactionDetails(receiveMoneyOutputData outputData);


    /**
     * Presents a confirmation of a successful transaction, including the amount, currency, and updated balance.
     * Implementing classes should handle the logic to display a success message and transaction details.
     *
     * @param amount          The amount of money received in the transaction.
     * @param currency        The currency of the transaction.
     * @param updatedBalance  The updated account balance after receiving the money.
     */
    void presentTransactionConfirmation(Double amount, String currency, Double updatedBalance);

    /**
     * Presents an error message to the user.
     * Implementing classes should handle the logic to display or communicate the error message.
     *
     * @param message The error message to be presented.
     */
    void presentError(String message);
}

