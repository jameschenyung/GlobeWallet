package interface_adapter.EmailSender;
public interface EmailSenderGateway {
    void sendEmail(String recipientEmail, String emailSubject, String emailMessage);

    void sendWelcomeEmail(String email, String name);

    void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver);

    void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver);

    }

