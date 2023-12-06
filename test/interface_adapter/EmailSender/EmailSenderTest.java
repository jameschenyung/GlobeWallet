package interface_adapter.EmailSender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class EmailSenderTest {

    private EmailSender emailSender;

    @BeforeEach
    void setUp() {
        emailSender = new EmailSender() {
            @Override
            public void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {
                // Override to prevent actual email sending
                System.out.println("Mock email sent to: " + recipientEmail);
            }
        };
    }


    @Test
    void sendWelcomeEmail() {
        String email = "test@example.com";
        String name = "Test User";
        emailSender.sendWelcomeEmail(email, name);
    }

    @Test
    void sendTransactionSender() {
        // Simulate sending an email about a transaction initiated by the user
        emailSender.sendTransactionSender("sender@example.com", 12345, 100.0, "USD", "Sender", "Receiver");

        // Similar to above, assertions would be limited
    }

    @Test
    void sendTransactionReceiver() {
        // Simulate sending an email about a transaction where the user is the receiver
        emailSender.sendTransactionReceiver("receiver@example.com", 12345, 100.0, "USD", "Sender", "Receiver");

        // Similar to above, assertions would be limited
    }

    @Test
    void sendUpdateConfirmation() {
        // Simulate sending an email confirming a profile update
        emailSender.sendUpdateConfirmation("user@example.com", "User");

        // Similar to above, assertions would be limited
    }
}
