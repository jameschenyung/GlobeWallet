package use_case.sendmoneytransfer;

public class SendMoneyInputData {
        private String senderId;
        private String receiverId;
        private double amount;
        private String currency;
        private Integer securityCode;

        public SendMoneyInputData(String senderId, String receiverId, double amount, String currency, Integer securityCode) {
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.amount = amount;
            this.currency = currency;
            this.securityCode = securityCode;
        }

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Integer getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(Integer securityCode) {
            this.securityCode = securityCode;
        }
    }



