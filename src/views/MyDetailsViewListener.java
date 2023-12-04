package views;

/**
 * Interface defining the contract for a listener in the MyDetails view.
 * Implementers of this interface handle actions related to loading and displaying user details.
 * Typically used to link the view with the underlying data and business logic.
 */
public interface MyDetailsViewListener {
    /**
     * Called to load and display the details of a user based on their user ID.
     * Implementing classes should handle the retrieval and presentation of the user's details.
     *
     * @param userId The unique identifier of the user whose details are to be loaded.
     */
    void onLoadUserDetails(int userId);
}
