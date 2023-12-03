package interface_adapter.EmailSender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailSenderTest {

    @Test
    void testSendEmail() {
        // Arrange
        String to = "jameschenyung@gmail.com";
        String subject = "Testing JavaMail API";
        String message = "Hello, this is a test email sent from Java!";

        // Act & Assert
        assertDoesNotThrow(() -> EmailSender.sendEmail(to, subject, message));
    }

    @Test
    void testSendWelcomeEmail() {
        // Arrange
        String to = "jameschenyung@gmail.com";
        String name = "James";

        // Act & Assert
        assertDoesNotThrow(() -> EmailSender.sendWelcomeEmail(to, name));
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
        assertDoesNotThrow(() -> EmailSender.sendTransactionReceiver(to, transactionId, amount, currency, senderName, receiverName));
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
        assertDoesNotThrow(() -> EmailSender.sendTransactionSender(to, transactionId, amount, currency, senderName, receiverName));
    }
}