package usecaseTest;

import objects.User;
import use_case.login.LoginUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class StubLoginUserDataAccess implements LoginUserDataAccessInterface {
    private Map<String, User> users = new HashMap<>();

    public void addUser(String username, String password) {
        users.put(username, new User(1, "John", "Doe", "johndoe", "password123", "USD", "johndoe@example.com")
        );
    }

    @Override
    public User getUserByUsername(String username) {
        return users.get(username);
    }

    @Override
    public boolean checkPassword(String username, String password) {
        return false;
    }

    @Override
    public void setCurrentUser(String username, Integer userid) {

    }
}
