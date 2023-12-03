package use_case.receiveMoney;


public interface receiveMoneyInputBoundary {
    /**
     * Verifies if the transaction exists and if the receiver's ID matches the receiver ID of the transaction.
     *
     * @param inputData input data
     */
    void verifyTransaction(receiveMoneyInputData inputData);

    /**
     * Confirms if the provided security code matches the one associated with the transaction.
     *
     * @param inputData input data
     */
    void confirmSecurityCode(receiveMoneyInputData inputData);
}

