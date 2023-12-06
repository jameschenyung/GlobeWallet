package use_case.updateUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UpdateUserInputDataTest {

    private UpdateUserInputData inputData;

    @BeforeEach
    void setUp() {
        inputData = new UpdateUserInputData(null, null, null, null);
    }

    @Test
    void testSetAndGetNewUsername() {
        String newUsername = "newUsername";
        inputData.setNewUsername(newUsername);
        assertEquals(newUsername, inputData.getNewUsername(), "The getter for newUsername should return the value set by the setter.");
    }

    @Test
    void testSetAndGetNewEmail() {
        String newEmail = "newEmail@example.com";
        inputData.setNewEmail(newEmail);
        assertEquals(newEmail, inputData.getNewEmail(), "The getter for newEmail should return the value set by the setter.");
    }

    @Test
    void testSetAndGetNewPassword() {
        String newPassword = "newPassword123";
        inputData.setNewPassword(newPassword);
        assertEquals(newPassword, inputData.getNewPassword(), "The getter for newPassword should return the value set by the setter.");
    }

    @Test
    void testSetAndGetRepeatNewPassword() {
        String repeatNewPassword = "newPassword123";
        inputData.setRepeatNewPassword(repeatNewPassword);
        assertEquals(repeatNewPassword, inputData.getRepeatNewPassword(), "The getter for repeatNewPassword should return the value set by the setter.");
    }
}
