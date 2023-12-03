package interface_adapter.EmailSender;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender implements EmailSenderGateway{

    public static void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {
        // Sender's email and password
        final String username = "globewallet@outlook.com";
        final String password = "Abc12345678!";

        // Properties for the mail session
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-mail.outlook.com"); // Outlook SMTP server
        props.put("mail.smtp.port", "587"); // Default SMTP port for TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a mail session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(username));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set Subject: header field
            message.setSubject(emailSubject);

            // Set the actual message
            message.setText(emailMessage);

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendWelcomeEmail(String email) {
        sendEmail(email,
                "Welcome to GlobeWallet",
                "Thank you for joining us, we ensure you a wonderful experience now that you have joined our group.");
    }

    public static void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {
        sendEmail(
                email,
                "Your money transfer to " + receiver + " was sucessful",
                "Hi " + sender + ", " +
                        "\nYour money transfer of " + Double.toString(amount) + " " + currency + " to " + receiver + " was successful." +
                        "\nYour transaction number is " + Integer.toString(transactionId) + "."
        );
    }

    public static void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {
        sendEmail(
                email,
                "You have received money from " + sender,
                "Hi " + receiver + ", " +
                        "\nYou have received " + Double.toString(amount) + " " + currency + " from " + sender + "." +
                        "\nYour transaction number is " + Integer.toString(transactionId) + "."
        );
    }

    public static void main(String[] args) {
        // Example usage
        String to = "jameschenyung@gmail.com";
        String subject = "Testing JavaMail API";
        String message = "Hello, this is a test email sent from Java!";

        // sendEmail(to, subject, message);
        //sendWelcomeEmail("jameschenyung@gmail.com");
        sendTransactionReceiver(to, 90, 34.0, "CAD", "James", "Kesier");
    }
}

