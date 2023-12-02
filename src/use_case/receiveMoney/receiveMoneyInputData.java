package use_case.receiveMoney;

import views.ReceiveMoneyPanel;
import use_case.receiveMoney.receiveMoneyDataAccessInterface;

public class receiveMoneyInputData {
    private Integer transactionId;
    private Integer securityCode;
    private receiveMoneyDataAccessInterface receiveMoneyDataAccess;

    public receiveMoneyInputData(Integer transactionId, Integer securityCode) {
        this.transactionId = transactionId;
        this.securityCode = securityCode;
    }


    public Integer getInputSecurityCode() {
        return securityCode;
    }

    public void getInputSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }
}
