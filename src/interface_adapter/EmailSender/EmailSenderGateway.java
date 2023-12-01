package interface_adapter.EmailSender;
public interface EmailSenderGateway {
    default void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {
    }
}
