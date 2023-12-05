package use_case.sendmoneytransfer;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import interface_adapter.EmailSender.EmailSenderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SendMoneyTest {
    private SendMoneyUserDataAccessInterface userDataAccess;
    private SendMoneyOutputBoundary outputBoundary;
    private CurrencyConversionGateway conversionGateway;
    private EmailSenderGateway emailSenderGateway;
    private SendMoneyInteractor sendMoneyInteractor;

    @BeforeEach
    void setUp() throws SQLException {
        if (!(userDataAccess.isUsernameTaken("user1"))){
            userDataAccess.createUser(
                    "James",
                    "Yung",
                    "user1",
                    "Abc12345",
                    "CAD",
                    "jameschenyung@gmail.com"
            );
        }

        if (userDataAccess.isValidAccount(1111)) {
            userDataAccess.updateAccountBalance(1111, 21.30);
        }
        else {
            userDataAccess.saveAccount(
                    1111,
                    userDataAccess.getUserIdFromUserName("user1"),
                    21.30,
                    "CAD"
            );
        }

        if (!(userDataAccess.isUsernameTaken("user2"))) {
            userDataAccess.createUser(
                    "Keiser",
                    "Jiang",
                    "user2",
                    "Def12345",
                    "CAD",
                    "keiser.jiang@gmail.com"
            );
        }

        if (userDataAccess.isValidAccount(2222)) {
            userDataAccess.updateAccountBalance(2222, 10.50);
        }
        else {
            userDataAccess.saveAccount(
                    2222,
                    userDataAccess.getUserIdFromUserName("user2"),
                    10.50,
                    "CAD"
            );
        }

        if (!(userDataAccess.isUsernameTaken("user3"))) {
            userDataAccess.createUser(
                    "Yoyo",
                    "Gong",
                    "user3",
                    "Ghi12345",
                    "USD",
                    "yoyogongtian@gmail.com"
            );
        }

        if (userDataAccess.isValidAccount(3333)) {
            userDataAccess.updateAccountBalance(3333, 42.10);
        }
        else {
            userDataAccess.saveAccount(
                    3333,
                    userDataAccess.getUserIdFromUserName("user3"),
                    42.10,
                    "USD"
            );
        }
    }

    @Test
    void testSendMoneySuccess() {

        // Arrange
        double amount = 11.30;
        SendMoneyInputData input = new SendMoneyInputData(1111, 2222, amount, 123);

        // Act
        sendMoneyInteractor.convert(input);
        sendMoneyInteractor.transfer(input);

        Double JamesBalance = userDataAccess.getAccountBalance(1111);
        Double KeiserBalance = userDataAccess.getAccountBalance(2222);

        // Assert
        assertTrue(JamesBalance == 10.0 && KeiserBalance == 21.80);
    }

//    @Test
//    void testConvertSuccess() {
//        // Arrange
//        SendMoneyInputData input = new SendMoneyInputData(/* provide valid input */);
//
//        // Act
//        sendMoneyInteractor.convert(input);
//
//        // Assert
//        assertTrue(((TestSendMoneyOutputBoundary) outputBoundary).isPrepareSuccessConvertCalled());
//    }
//
//    @Test
//    void testTransferSuccess() {
//        // Arrange
//        SendMoneyInputData input = new SendMoneyInputData(/* provide valid input */);
//
//        // Act
//        sendMoneyInteractor.transfer(input);
//
//        // Assert
//        assertTrue(((TestSendMoneyOutputBoundary) outputBoundary).isPrepareSuccessTransferCalled());
//    }

    // Add more tests for failure scenarios, edge cases, etc.
}
