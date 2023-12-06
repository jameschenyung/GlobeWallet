import Entity.*;
import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {
    private final User user = new User(
            1,
            "James",
            "Yung",
            "jamesyung",
            "Abc12345",
            "CAD",
            "jameschenyung@gmail.com"
    );

    private final Account account = new Account(
            5,
            1,
            50.0,
            "CAD"
    );

    private final User user2 = new User(
            2,
            "Keiser",
            "Jiang",
            "keiserjiang",
            "Def12345",
            "USD",
            "keiser.jiang@gmail.com"
    );

    private final Account account2 = new Account(
            6,
            2,
            25.0,
            "USD"
    );

    private final Transaction transaction = new Transaction(
            3,
            1,
            2,
            25.0,
            456,
            0
    );

    @Test
    void getUserId() {
        assertEquals(1,user.getUserId());
    }

    @Test
    void addAccount() {
        user.addAccount(account);
        assertTrue(user.getAccounts().isEmpty());
    }

    @Test
    void getFullName() {
        String full = "James Yung";
        assertEquals(full, user.getFullName());
    }

    @Test
    void getAccountId() {
        assertEquals(5, account.getAccountId());
    }

    @Test
    void getBalance() {
        assertEquals(50.0, account.getBalance());
    }

    @Test
    void getUserIdFromAccount() {
        assertEquals(1, account.getUserid());
    }

    @Test
    void getCurrencyType() {
        assertEquals("CAD", account.getCurrencyType());
    }

    @Test
    void getTransactionId() {
        assertEquals(3, transaction.getTransactionId());
    }

    @Test
    void getAmount() {
        assertEquals(25.0, transaction.getAmount());
    }

}
