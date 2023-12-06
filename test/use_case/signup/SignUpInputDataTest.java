package use_case.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SignUpInputDataTest {

    private SignUpInputData inputData;

    @BeforeEach
    void setUp() {
        inputData = new SignUpInputData(null, null, null, null, null, null, null);
    }

    @Test
    void setAndGetFirstName() {
        String firstName = "John";
        inputData.setFirstName(firstName);
        assertEquals(firstName, inputData.getFirstName(), "The retrieved first name should match the set value.");
    }

    @Test
    void setAndGetLastName() {
        String lastName = "Doe";
        inputData.setLastName(lastName);
        assertEquals(lastName, inputData.getLastName(), "The retrieved last name should match the set value.");
    }

    @Test
    void setAndGetUsername() {
        String username = "johndoe";
        inputData.setUsername(username);
        assertEquals(username, inputData.getUsername(), "The retrieved username should match the set value.");
    }

    @Test
    void setAndGetEmail() {
        String email = "john@example.com";
        inputData.setEmail(email);
        assertEquals(email, inputData.getEmail(), "The retrieved email should match the set value.");
    }

    @Test
    void setAndGetRepeatEmail() {
        String repeatEmail = "john@example.com";
        inputData.setRepeatEmail(repeatEmail);
        assertEquals(repeatEmail, inputData.getRepeatEmail(), "The retrieved repeat email should match the set value.");
    }

    @Test
    void setAndGetPassword() {
        String password = "password123";
        inputData.setPassword(password);
        assertEquals(password, inputData.getPassword(), "The retrieved password should match the set value.");
    }

    @Test
    void setAndGetRepeatPassword() {
        String repeatPassword = "password123";
        inputData.setRepeatPassword(repeatPassword);
        assertEquals(repeatPassword, inputData.getRepeatPassword(), "The retrieved repeat password should match the set value.");
    }
}
