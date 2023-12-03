package use_case.emailSender;

import objects.Account;
/**
 * Interface defining the contract for an input boundary in the email sending use case.
 * This interface should be implemented by any class that handles the logic for sending emails,
 * specifically for sending a welcome email to new account holders.
 */
public interface EmailInputBoundary {
    /**
     * Sends a welcome email to the holder of a newly created account.
     * Implementing classes should handle the process of composing and sending the welcome email
     * based on the information provided in the account object.
     *
     * @param account The account of the user to whom the welcome email will be sent.
     */
    void sendWelcomeEmail(Account account);
}
