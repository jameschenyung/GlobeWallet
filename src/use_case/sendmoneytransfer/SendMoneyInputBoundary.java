package use_case.sendmoneytransfer;

public interface SendMoneyInputBoundary {
    /**
     * check if account exist and preparesuccessview of its currency type
     * @param sendMoneyInputData inputdata
     */
    void checkAccount(SendMoneyInputData sendMoneyInputData);

    /**
     * in charge of currency convertion that convert the amount entered to the sender's currency type
     * and then check if the sender has enough balance for this
     * @param sendMoneyInputData inputdata
     */
    void convert(SendMoneyInputData sendMoneyInputData);

    /**
     * in charge of update accountbalance after transfer and create a transaction
     * @param sendMoneyInputData inputdata
     */
    void transfer(SendMoneyInputData sendMoneyInputData);
}
