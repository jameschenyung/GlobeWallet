package interface_adapter.EmailSender;
/**
 * Interface defining the contract for email sending functionalities.
 * This interface should be implemented by classes that provide functionality
 * to send different types of emails, such as welcome emails and transaction notifications.
 */
public interface EmailSenderGateway {

    /**
     * Sends an email to a specified recipient.
     * Implementers should provide the functionality to send an email with a subject and message body.
     *
     * @param recipientEmail The email address of the recipient.
     * @param emailSubject   The subject line of the email.
     * @param emailMessage   The body of the email.
     */
    static void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {
    }

    /**
     * Sends a welcome email to a new user.
     * Implementers should define the content of the welcome email.
     *
     * @param email The email address of the recipient.
     */
    static void sendWelcomeEmail(String email) {

    }

    /**
     * Sends an email notification to the sender of a transaction.
     * The email should contain details of the transaction such as the amount, currency, and recipient.
     *
     * @param email          The email address of the sender.
     * @param transactionId  The ID of the transaction.
     * @param amount         The amount of the transaction.
     * @param currency       The currency of the transaction.
     * @param sender         The name of the sender.
     * @param receiver       The name of the receiver.
     */
    static void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

    }

    /**
     * Sends an email notification to the receiver of a transaction.
     * The email should contain details of the transaction such as the amount, currency, and sender.
     *
     * @param email          The email address of the receiver.
     * @param transactionId  The ID of the transaction.
     * @param amount         The amount of the transaction.
     * @param currency       The currency of the transaction.
     * @param sender         The name of the sender.
     * @param receiver       The name of the receiver.
     */
    static void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {

    }
}
