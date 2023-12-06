//package usecaseTest;
//
//import database.DataAccessObject;
//import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
//import interface_adapter.EmailSender.EmailSenderGateway;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import use_case.sendmoneytransfer.SendMoneyInputData;
//import use_case.sendmoneytransfer.SendMoneyInteractor;
//import use_case.sendmoneytransfer.SendMoneyOutputBoundary;
//import use_case.sendmoneytransfer.SendMoneyUserDataAccessInterface;
//import database.DataAccessObject.*;
//
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SendMoneyTest {
//    private SendMoneyUserDataAccessInterface userDataAccess;
//    private SendMoneyOutputBoundary outputBoundary;
//    private CurrencyConversionGateway conversionGateway;
//    private EmailSenderGateway emailSenderGateway;
//    private SendMoneyInteractor sendMoneyInteractor;
//    private DataAccessObject dataAccessObject;
//
//    @BeforeEach
//    void setUp() throws SQLException {
//        if (!(dataAccessObject.isUsernameTaken("user1"))){
//            dataAccessObject.createUser(
//                    "James",
//                    "Yung",
//                    "user1",
//                    "Abc12345",
//                    "CAD",
//                    "jameschenyung@gmail.com"
//            );
//        }
//
//        if (userDataAccess.isValidAccount(1111)) {
//            userDataAccess.updateAccountBalance(1111, 21.30);
//        }
//        else {
//            dataAccessObject.saveAccount(
//                    1111,
//                    dataAccessObject.getUserIdFromUserName("user1"),
//                    21.30,
//                    "CAD"
//            );
//        }
//
//        if (!(dataAccessObject.isUsernameTaken("user2"))) {
//            dataAccessObject.createUser(
//                    "Keiser",
//                    "Jiang",
//                    "user2",
//                    "Def12345",
//                    "CAD",
//                    "keiser.jiang@gmail.com"
//            );
//        }
//
//        if (userDataAccess.isValidAccount(2222)) {
//            userDataAccess.updateAccountBalance(2222, 10.50);
//        }
//        else {
//            dataAccessObject.saveAccount(
//                    2222,
//                    dataAccessObject.getUserIdFromUserName("user2"),
//                    10.50,
//                    "CAD"
//            );
//        }
//
//        if (!(dataAccessObject.isUsernameTaken("user3"))) {
//            dataAccessObject.createUser(
//                    "Yoyo",
//                    "Gong",
//                    "user3",
//                    "Ghi12345",
//                    "USD",
//                    "yoyogongtian@gmail.com"
//            );
//        }
//
//        if (userDataAccess.isValidAccount(3333)) {
//            userDataAccess.updateAccountBalance(3333, 42.10);
//        }
//        else {
//            dataAccessObject.saveAccount(
//                    3333,
//                    dataAccessObject.getUserIdFromUserName("user3"),
//                    42.10,
//                    "USD"
//            );
//        }
//    }
//
//    @Test
//    void testSendMoneySuccess() {
//
//        // Arrange
//        double amount = 11.30;
//        SendMoneyInputData input = new SendMoneyInputData(1111, 2222, amount, 123);
//
//        // Act
//        sendMoneyInteractor.convert(input);
//        sendMoneyInteractor.transfer(input);
//
//        Double JamesBalance = userDataAccess.getAccountBalance(1111);
//        Double KeiserBalance = userDataAccess.getAccountBalance(2222);
//
//        // Assert
//        assertTrue(JamesBalance == 10.0 && KeiserBalance == 21.80);
//    }
//
//    @Test
//    void testSendMoneySuccess2() {
//        double amount = 22.10;
//        SendMoneyInputData input = new SendMoneyInputData(3333, 1111, amount, 321);
//
//        // Act
//        sendMoneyInteractor.convert(input);
//        sendMoneyInteractor.transfer(input);
//
//        Double YoyoBalance = userDataAccess.getAccountBalance(3333);
//        Double JamesBalance = userDataAccess.getAccountBalance(1111);
//
//        // Assert
//        assertTrue(YoyoBalance == 20.0 && (50 <= JamesBalance && JamesBalance <= 54));
//    }
//}
//
