package databaseTest;

import database.DataAccessObject;
import database.UpdateUserDataAccessObject;
import database.signupDataAccessObject;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the UpdateUserDataAccessObject class.
 */
class UpdateUserDataAccessObjectTest {

    /**
     * Test for the isUsernameTaken method.
     */

    @Test
    void isUsernameTaken() {
        UpdateUserDataAccessObject userDao = new UpdateUserDataAccessObject();

        signupDataAccessObject dao = new signupDataAccessObject();

        dao.createUser(
                "James",
                "Yung",
                "jamesyung123456",
                "Abcdefg1234567",
                "CAD",
                "jameschenyung@gmail.com"
        );

        // Test when the username is taken
        boolean taken = userDao.isUsernameTaken("jamesyung123456");
        assertTrue(taken, "Existing username should be taken.");

        // Test when the username is not taken
        taken = userDao.isUsernameTaken("newUsername");
        assertFalse(taken, "New username should not be taken.");
    }

    /**
     * Test for the validatePassword method.
     */
    @Test
    void validatePassword() {
        UpdateUserDataAccessObject userDao = new UpdateUserDataAccessObject();

        // Test a valid password
        boolean isValid = userDao.validatePassword("ValidPassword123");
        assertTrue(isValid, "The password should be valid.");

        // Test an invalid password
        isValid = userDao.validatePassword("weak");
        assertFalse(isValid, "The password should be invalid.");
    }

    /**
     * Test for the updateUser method.
     */
    @Test
    void updateUser() throws SQLException {
        UpdateUserDataAccessObject userDao = new UpdateUserDataAccessObject();
        signupDataAccessObject dao = new signupDataAccessObject();
        DataAccessObject dataAccessObject = new DataAccessObject();

        dao.createUser(
                "James",
                "Yung",
                "jamesyung123",
                "Abcdefg1234567",
                "CAD",
                "jameschenyung@gmail.com"
        );

        int id = dataAccessObject.getUserIdFromUserName("jamesyung123");
        userDao.updateUser(id, "jameschenyung@gmail.com", "jamesyung1", "Abcdefg1234567");

        assertTrue(userDao.isUsernameTaken("jamesyung1"));
    }

    /**
     * Test for the getUserByUsername method.
     */
    @Test
    void getUserByUsername() {
        UpdateUserDataAccessObject userDao = new UpdateUserDataAccessObject();

        // Test when the user exists
        // Must create this use in database before running
        //String username = "existingUser";
        //assertNotNull(userDao.getUserByUsername(username), "User should exist.");

        // Test when the user does not exist
        String username = "nonExistingUser";
        assertNull(userDao.getUserByUsername(username), "User should not exist.");
    }

    /**
     * Test for the getCurrentUserId method.
     */

    @Test
    void getUserIdFromUserName() {
        UpdateUserDataAccessObject userDao = new UpdateUserDataAccessObject();

        signupDataAccessObject dao = new signupDataAccessObject();

        dao.createUser(
                "James",
                "Yung",
                "jamesyung12",
                "Abcdefg1234567",
                "CAD",
                "jameschenyung@gmail.com"
        );

        // Test when the user exists
        String username = "jamesyung12";
        assertNotNull(userDao.getUserIdFromUserName(username), "User ID should exist.");

        // Test when the user does not exist
        username = "nonExistingUser";
        assertNull(userDao.getUserIdFromUserName(username), "User ID should not exist.");
    }

    /**
     * Test for the getFullName method.
     */
    @Test
    void getFullName() {
        UpdateUserDataAccessObject userDao = new UpdateUserDataAccessObject();

        signupDataAccessObject dao = new signupDataAccessObject();

        dao.createUser(
                "James",
                "Yung",
                "jamesyung12",
                "Abcdefg1234567",
                "CAD",
                "jameschenyung@gmail.com"
        );

        int id = userDao.getUserIdFromUserName("jamesyung12");

        assertNotNull(userDao.getFullName(id), "Full name should exist.");
    }
}
