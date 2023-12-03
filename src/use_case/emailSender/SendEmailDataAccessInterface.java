package use_case.emailSender;

import objects.User;

/**
 * Interface defining the contract for data access operations in the email sending use case.
 * This interface should be implemented by any class that handles data retrieval necessary
 * for sending emails, particularly retrieving user information like email addresses.
 */
public interface SendEmailDataAccessInterface {

    /**
     * Retrieves the user's email address.
     * Implementing classes should give  the functionality to access the email address of a user
     * from the data store, which is required for sending emails.
     *
     * @return The User object containing the email address.
     */
    User getEmail();
}
