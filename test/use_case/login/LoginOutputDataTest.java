package use_case.login;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the {@link LoginOutputData} class.
 * These tests cover various aspects of the LoginOutputData class, including message retrieval,
 * string representation, and constructor behavior.
 */
class LoginOutputDataTest {
    /**
     * Test the {@link LoginOutputData#getMessage()} method.
     * Verifies that the method returns the correct message.
     */
    @Test
    void getMessage() {
        boolean success = true;
        String message = "Login successful";

        LoginOutputData outputData = new LoginOutputData(success, message);

        assertEquals(message, outputData.getMessage(), "The getMessage method should return the correct message.");
    }

    /**
     * Test the {@link LoginOutputData#toString()} method.
     * Verifies that the method returns the correct string representation.
     */
    @Test
    void testToString() {
        boolean success = true;
        String message = "Login successful";

        LoginOutputData outputData = new LoginOutputData(success, message);
        String expectedString = "LoginOutputData{success=" + success + ", message='" + message + "'}";

        assertEquals(expectedString, outputData.toString(), "The toString method should return the correct string representation.");
    }

    /**
     * Test the constructor behavior when a null message is provided.
     * Verifies that the constructor throws an IllegalArgumentException.
     */
    @Test
    void constructorThrowsExceptionOnNullMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LoginOutputData(true, null);
        }, "Constructor should throw IllegalArgumentException for null message.");
    }

    /**
     * Test the constructor behavior when an empty message is provided.
     * Verifies that the constructor throws an IllegalArgumentException.
     */
    @Test
    void constructorThrowsExceptionOnEmptyMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LoginOutputData(true, "");
        }, "Constructor should throw IllegalArgumentException for empty message.");
    }
}
