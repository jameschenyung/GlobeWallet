package use_case.signup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SignupOutputDataTest {

    @Test
    void testSuccess() {
        boolean success = true;
        String message = "Signup successful";
        int userId = 123;

        SignupOutputData outputData = new SignupOutputData(success, message, userId);

        assertEquals(success, outputData.isSuccess(), "The isSuccess method should return the correct success status.");
    }

    @Test
    void testMessage() {
        boolean success = true;
        String message = "Signup successful";
        int userId = 123;

        SignupOutputData outputData = new SignupOutputData(success, message, userId);

        assertEquals(message, outputData.getMessage(), "The getMessage method should return the correct message.");
    }

    @Test
    void testUserId() {
        boolean success = true;
        String message = "Signup successful";
        int userId = 123;

        SignupOutputData outputData = new SignupOutputData(success, message, userId);

        assertEquals(userId, outputData.getUserId(), "The getUserId method should return the correct user ID.");
    }
}
