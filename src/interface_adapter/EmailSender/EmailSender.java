package interface_adapter.EmailSender;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    public static void main(String[] args) {
        // Sender's email and password
        final String username = "globewallet@outlook.com";
        final String password = "Abc12345678!";

        // Recipient's email address
        String to = "jameschenyung@gmail.com";

        // Properties for the mail session
        /*
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
         */

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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing JavaMail API");

            // Set the actual message
            message.setText("Hello, this is a test email sent from Java!");

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

