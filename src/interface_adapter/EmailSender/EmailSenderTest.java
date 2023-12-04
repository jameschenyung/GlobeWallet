package interface_adapter.EmailSender;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for EmailSender.
 * This class uses JUnit tests to verify that the methods of the EmailSender class do not throw exceptions
 * during their execution. Each test corresponds to a different method in the EmailSender class.
 */
public class EmailSenderTest {

    /**
     * Tests the sendEmail method of the EmailSender.
     * Ensures that calling sendEmail with valid parameters does not result in an exception.
     */
    @Test
    void testSendEmail() {
        // Arrange
        String to = "jameschenyung@gmail.com";
        String subject = "Testing JavaMail API";
        String message = "Hello, this is a test email sent from Java!";

        // Act & Assert
        assertDoesNotThrow(() -> EmailSender.sendEmail(to, subject, message));
    }

    /**
     * Tests the sendWelcomeEmail method of the EmailSender.
     * Ensures that calling sendWelcomeEmail with valid parameters does not result in an exception.
     */
    @Test
    void testSendWelcomeEmail() {
        // Arrange
        String to = "jameschenyung@gmail.com";
        String name = "James";

        // Act & Assert
        assertDoesNotThrow(() -> EmailSender.sendWelcomeEmail(to, name));
    }


    /**
     * Tests the sendTransactionReceiver method of the EmailSender.
     * Ensures that calling sendTransactionReceiver with valid parameters does not result in an exception.
     */
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

    /**
     * Tests the sendTransactionSender method of the EmailSender.
     * Ensures that calling sendTransactionSender with valid parameters does not result in an exception.
     */
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