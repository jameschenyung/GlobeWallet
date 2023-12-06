package use_case.login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginOutputDataTest {

    @Test
    void getMessage() {
        boolean success = true;
        String message = "Login successful";

        LoginOutputData outputData = new LoginOutputData(success, message);

        assertEquals(message, outputData.getMessage(), "The getMessage method should return the correct message.");
    }

    @Test
    void testToString() {
        boolean success = true;
        String message = "Login successful";

        LoginOutputData outputData = new LoginOutputData(success, message);
        String expectedString = "LoginOutputData{success=" + success + ", message='" + message + "'}";

        assertEquals(expectedString, outputData.toString(), "The toString method should return the correct string representation.");
    }

    @Test
    void constructorThrowsExceptionOnNullMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LoginOutputData(true, null);
        }, "Constructor should throw IllegalArgumentException for null message.");
    }

    @Test
    void constructorThrowsExceptionOnEmptyMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LoginOutputData(true, "");
        }, "Constructor should throw IllegalArgumentException for empty message.");
    }
}
