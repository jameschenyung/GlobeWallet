package use_case.emailSender;

import interface_adapter.EmailSender.EmailSenderGateway;
import objects.Account;

public class SendWelcomeEmail implements EmailInputBoundary {
    private final EmailSenderGateway emailService;

    public SendWelcomeEmail(EmailSenderGateway emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendWelcomeEmail(Account account) {
        String subject = "Welcome to GlobeWallet";
        String message = "Thank you for joining us";
        emailService.sendEmail(account.getEmail(), subject, message);
    }
}
