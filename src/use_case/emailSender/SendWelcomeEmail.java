package use_case.emailSender;

import database.DataAccessObject;
import interface_adapter.EmailSender.EmailSenderGateway;
import objects.Account;
/**
 * Class responsible for implementing the logic to send welcome emails to new account holders.
 * This class utilizes an email service and data access interface to compose and send welcome emails.
 */
public class SendWelcomeEmail implements EmailInputBoundary {
    private final EmailSenderGateway emailService;
    private final SendEmailDataAccessInterface dataAccess;

    /**
     * Constructs a SendWelcomeEmail instance with the specified email service and data access interface.
     *
     * @param emailService The email service gateway to send emails.
     * @param dataAccess The data access interface to retrieve necessary information for email sending.
     */
    public SendWelcomeEmail(EmailSenderGateway emailService, SendEmailDataAccessInterface dataAccess) {
        this.emailService = emailService;
        this.dataAccess = dataAccess;
    }

    /**
     * Sends a welcome email to a new account holder.
     * This method composes a welcome message and uses the email service to send it to the account's email address.
     *
     * @param account The account of the user to whom the welcome email will be sent.
     */
    @Override
    public void sendWelcomeEmail(Account account) {
        String subject = "Welcome to GlobeWallet";
        String message = "Thank you for joining us, we ensure you a wonderful experience now that you have joined our group.";
        emailService.sendEmail(dataAccess.getEmail(), subject, message);
    }
}
