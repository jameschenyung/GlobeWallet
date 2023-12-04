package presenter;

import views.MyDetailsViewListener;
import views.MyDetailsPanel;
import objects.User;
/**
 * Presenter class in an MVP architecture responsible for handling the presentation logic for user details.
 * This class communicates between the MyDetailsPanel view and the data model, responding to user actions
 * and updating the view as needed.
 */
public class MyDetailsPresenter implements MyDetailsViewListener {
    private MyDetailsPanel view;

    /**
     * Constructs a MyDetailsPresenter with a specific view.
     * It registers itself as a listener to the provided view to handle user interactions.
     *
     * @param view The MyDetailsPanel view this presenter will manage.
     */
    public MyDetailsPresenter(MyDetailsPanel view) {
        this.view = view;
        this.view.registerListener(this);
    }

    /**
     * Callback method triggered when user details need to be loaded.
     * This method should contain the logic to fetch and display user details based on the provided userId.
     *
     * @param userId The unique identifier of the user whose details are to be loaded.
     */
    @Override
    public void onLoadUserDetails(int userId) {

    }
}
