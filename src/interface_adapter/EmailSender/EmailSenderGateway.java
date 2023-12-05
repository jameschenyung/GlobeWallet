package interface_adapter.EmailSender;

/**
 * Interface defining the contract for an email sender gateway.
 * This interface outlines methods for sending various types of emails, such as welcome emails,
 * and notifications for transactions both as a sender and a receiver.
 */
public interface EmailSenderGateway {

    /**
     * Sends an email to a specified recipient with a given subject and message.
     *
     * @param recipientEmail The email address of the recipient.
     * @param emailSubject   The subject line of the email.
     * @param emailMessage   The content of the email message.
     */
    void sendEmail(String recipientEmail, String emailSubject, String emailMessage);

    /**
     * Sends a welcome email to a new user.
     *
     * @param email The email address of the new user.
     * @param name  The name of the new user to personalize the welcome message.
     */
    void sendWelcomeEmail(String email, String name);

    /**
     * Sends an email notification to the sender of a transaction, confirming the successful transfer.
     *
     * @param email         The email address of the sender.
     * @param transactionId The unique identifier of the transaction.
     * @param amount        The amount of money transferred.
     * @param currency      The currency of the transferred amount.
     * @param sender        The name of the sender.
     * @param receiver      The name of the receiver.
     */
    void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver);


    /**
     * Sends an email notification to the receiver of a transaction, informing them of the incoming transfer.
     *
     * @param email         The email address of the receiver.
     * @param transactionId The unique identifier of the transaction.
     * @param amount        The amount of money received.
     * @param currency      The currency of the received amount.
     * @param sender        The name of the sender.
     * @param receiver      The name of the receiver.
     */
    void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver);

    /**
     *
     * @param email
     * @param name
     */
    void sendUpdateConfirmation(String email, String name);
}



