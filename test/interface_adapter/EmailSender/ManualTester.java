package interface_adapter.EmailSender;

import interface_adapter.EmailSender.*;

/**
 * Manual tester for us to test the email sending
 * functionality by calling on the function
 * directly.
 */

public class ManualTester {
    private static EmailSender emailSender;

    public static void main(String[] args) {
        // Example usage
        String to = "jameschenyung@gmail.com";
        String subject = "Testing JavaMail API";
        String message = "Hello, this is a test email sent from Java!";

        emailSender.sendEmail(to, subject, message);
        //sendWelcomeEmail("jameschenyung@gmail.com", "James");
        //sendTransactionReceiver(to, 90, 34.0, "CAD", "James", "Kesier");
    }
}
