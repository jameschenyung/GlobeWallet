package use_case.receiveMoney;


public interface receiveMoneyInputBoundary {
    /**
     * Verifies if the transaction exists and if the receiver's ID matches the receiver ID of the transaction.
     *
     * @param transactionId The ID of the transaction to verify.
     */
    void verifyTransaction(Integer transactionId);

    /**
     * Confirms if the provided security code matches the one associated with the transaction.
     *
     * @param transactionId The ID of the transaction to confirm.
     * @param securityCode  The security code provided by the receiver for confirmation.
     */
    void confirmSecurityCode(Integer transactionId, Integer securityCode);
}

