package interface_adapter.EmailSender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailSenderTest {
    EmailSenderGateway emailSenderGateway = new EmailSenderGateway() {
        @Override
        public void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {

        }

        @Override
        public void sendWelcomeEmail(String email, String name) {

        }

        @Override
        public void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

        }

        @Override
        public void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

        }
    };

    @Test
    void testSendEmail() {
        // Arrange
        String to = "jameschenyung@gmail.com";
        String subject = "Testing JavaMail API";
        String message = "Hello, this is a test email sent from Java!";

        // Act & Assert
        assertDoesNotThrow(() -> emailSenderGateway.sendEmail(to, subject, message));
    }

    @Test
    void testSendWelcomeEmail() {
        // Arrange
        String to = "jameschenyung@gmail.com";
        String name = "James";

        // Act & Assert
        assertDoesNotThrow(() -> emailSenderGateway.sendWelcomeEmail(to, name));
    }

    @Test
    void testSendTransactionReceiver() {
        // Arrange
        String to = "keiser.jiang@gmail.com";
        int transactionId = 90;
        double amount = 34.0;
        String currency = "CAD";
        String senderName = "James";
        String receiverName = "Kesier";

        // Act & Assert
        assertDoesNotThrow(() -> emailSenderGateway.sendTransactionReceiver(to, transactionId, amount, currency, senderName, receiverName));
    }

    @Test
    void testSendTransactionSender() {
        // Arrange
        String to = "jameschenyung@gmail.com";
        int transactionId = 90;
        double amount = 34.0;
        String currency = "CAD";
        String senderName = "James";
        String receiverName = "Kesier";

        // Act & Assert
        assertDoesNotThrow(() -> emailSenderGateway.sendTransactionSender(to, transactionId, amount, currency, senderName, receiverName));
    }
}