package interface_adapter.EmailSender;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Provides email sending capabilities for various purposes.
 * This class uses the JavaMail API to send emails for different scenarios such as
 * welcoming a new user or notifying about transactions.
 */
public class EmailSender implements EmailSenderGateway{

    /**
     * Sends an email to the specified recipient with the given subject and message.
     *
     * @param recipientEmail The email address of the recipient.
     * @param emailSubject   The subject line of the email.
     * @param emailMessage   The body text of the email.
     */
    public void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {

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


    /**
     * Sends a welcome email to a new user.
     *
     * @param email The email address of the recipient.
     * @param name  The name of the recipient to be included in the welcome message.
     */
    public void sendWelcomeEmail(String email, String name) {

        sendEmail(email,
                "Welcome to GlobeWallet",
                "Thank you for joining us " + name + ", we ensure you a wonderful experience now that you have joined our group.");
    }

    /**
     * Sends an email to notify a user about a successful transaction they initiated.
     *
     * @param email        The email address of the sender.
     * @param transactionId The transaction ID.
     * @param amount       The amount transferred.
     * @param currency     The currency of the transaction.
     * @param sender       The name of the sender.
     * @param receiver     The name of the receiver.
     */
    public void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {
        sendEmail(
                email,
                "Your money transfer to " + receiver + " was sucessful",
                "Hi " + sender + ", " +
                        "\nYour money transfer of " + Double.toString(amount) + " " + currency + " to " + receiver + " was successful." +
                        "\nYour transaction number is " + Integer.toString(transactionId) + "." +
                        "\n GlobeWallet Team"
        );
    }

    /**
     * Sends an email to notify a user about a transaction in which they are the receiver.
     *
     * @param email        The email address of the receiver.
     * @param transactionId The transaction ID.
     * @param amount       The amount received.
     * @param currency     The currency of the transaction.
     * @param sender       The name of the sender.
     * @param receiver     The name of the receiver.
     */
    public void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {
        sendEmail(
                email,
                "You have a pending transaction from " + sender,
                "Hi " + receiver + ", " +
                        "\nYou have received " + Double.toString(amount) + " " + currency + " from " + sender + "." +
                        "\nYour transaction number is " + Integer.toString(transactionId) + "." +
                        "\nPlease refer to the application to claim your money ^_^ Thank you!" +
                        "\nGlobeWallet Team"
        );
    }

    public void sendUpdateConfirmation(String email, String name) {
        sendEmail(
                email,
                "Your profile has been updated",
                "Hi " + name + " we would like to inform you that your profile update request has been successful"
        );
    }
}

