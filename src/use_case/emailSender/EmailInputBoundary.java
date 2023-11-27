package use_case.emailSender;

import objects.Account;

public interface EmailInputBoundary {
    void sendWelcomeEmail(Account account);
}
