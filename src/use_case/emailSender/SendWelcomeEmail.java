package use_case.emailSender;

import database.DataAccessObject;
import interface_adapter.EmailSender.EmailSenderGateway;
import objects.Account;

public class SendWelcomeEmail implements EmailInputBoundary {
    private final EmailSenderGateway emailService;
    private final SendEmailDataAccessInterface dataAccess;

    public SendWelcomeEmail(EmailSenderGateway emailService, SendEmailDataAccessInterface dataAccess) {
        this.emailService = emailService;
        this.dataAccess = dataAccess;
    }

    @Override
    public void sendWelcomeEmail(Account account) {
        String subject = "Welcome to GlobeWallet";
        String message = "Thank you for joining us, we ensure you a wonderful experience now that you have joined our group.";
        emailService.sendEmail(dataAccess.getEmail(), subject, message);
    }
}
