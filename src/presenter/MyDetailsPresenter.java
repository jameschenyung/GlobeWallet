package presenter;

import views.MyDetailsViewListener;
import views.MyDetailsPanel;
import objects.User;

public class MyDetailsPresenter implements MyDetailsViewListener {
    private MyDetailsPanel view;

    public MyDetailsPresenter(MyDetailsPanel view) {
        this.view = view;
        this.view.registerListener(this);
    }

    @Override
    public void onLoadUserDetails(int userId) {

    }
}
