package interfaceAdapterTest.EmailSender;

import interface_adapter.EmailSender.*;

public class ManualTester {
    private EmailSenderGateway emailSenderGateway;

    public static void main(String[] args) {
        // Example usage
        String to = "jameschenyung@gmail.com";
        String subject = "Testing JavaMail API";
        String message = "Hello, this is a test email sent from Java!";

        //emailSenderGateway.sendEmail(to, subject, message);
        //sendWelcomeEmail("jameschenyung@gmail.com", "James");
        //sendTransactionReceiver(to, 90, 34.0, "CAD", "James", "Kesier");
    }
}
