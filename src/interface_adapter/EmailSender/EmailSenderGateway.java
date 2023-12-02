package interface_adapter.EmailSender;
public interface EmailSenderGateway {
    static void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {
    }

    static void sendWelcomeEmail(String email) {

    }

    static void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

    }

    static void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

    }
}
