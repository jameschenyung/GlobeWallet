package use_case.receiveMoney;


public interface receiveMoneyInputBoundary {
    /**
     * Processes the receipt of money, including updating account balances.
     * @param receiveMoneyInputData input data for receiving money.
     */
    void receive(receiveMoneyInputData receiveMoneyInputData);
}
