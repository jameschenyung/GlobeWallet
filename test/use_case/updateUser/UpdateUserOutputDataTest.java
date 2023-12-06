package use_case.updateUser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UpdateUserOutputDataTest {

    @Test
    void getUserId() {
        // Predefined values for testing
        boolean success = true;
        String message = "Update successful";
        int userId = 123; // Example user ID

        // Create an instance of UpdateUserOutputData with the predefined values
        UpdateUserOutputData outputData = new UpdateUserOutputData(success, message, userId);

        // Assert that getUserId returns the expected value
        assertEquals(userId, outputData.getUserId(), "The getUserId method should return the correct user ID.");
    }
}
