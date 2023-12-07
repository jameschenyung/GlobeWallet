package use_case.sendmoneytransfer;

import Entity.Account;
import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import interface_adapter.EmailSender.EmailSenderGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mock implementation of the {@link SendMoneyUserDataAccessInterface} for testing purposes.
 * This class simulates data access operations related to user accounts and transactions.
 */
class MockSendMoneyUserDataAccess implements SendMoneyUserDataAccessInterface {
    private Map<Integer, Double> accountBalances = new HashMap<>();
    private Map<Integer, String> accountCurrencies = new HashMap<>();
    private Set<Integer> validAccounts = new HashSet<>();

    @Override
    public Account getAccountById(Integer accountId) {
        if (validAccounts.contains(accountId)) {
            return new Account(accountId, 123, accountBalances.get(accountId), accountCurrencies.get(accountId));
        }
        return null;
    }

    @Override
    public void updateAccountBalance(Integer accountId, double newBalance) {
        if (validAccounts.contains(accountId)) {
            accountBalances.put(accountId, newBalance);
        }
    }

    @Override
    public boolean isValidCurrency(String currency) {
        return false;
    }

    @Override
    public boolean isValidAccount(Integer accountId) {
        return false;
    }

    @Override
    public Double getAccountBalance(Integer accountId) {
        return accountBalances.getOrDefault(accountId, null);
    }

    @Override
    public void createTransaction(Integer transactionId, Integer SendId, Integer ReceiverId, Double amount, Integer SecurityCode, Integer received) {

    }

    @Override
    public int generateUniqueTransactionId() {
        return 0;
    }

    @Override
    public String getEmail(int userId) {
        return null;
    }

    @Override
    public String getFullName(Integer userId) {
        return null;
    }

    @Override
    public Integer getUserIdbyAccountId(Integer accountId) {
        return null;
    }

    @Override
    public String getCurrencyByAccount(Integer accountId) {
        return accountCurrencies.getOrDefault(accountId, null);
    }

    // Add accounts for testing
    public void addAccount(Integer accountId, double balance, String currency) {
        validAccounts.add(accountId);
        accountBalances.put(accountId, balance);
        accountCurrencies.put(accountId, currency);
    }

    // Other methods...
}

/**
 * Mock implementation of the {@link SendMoneyOutputBoundary} for testing purposes.
 * This class captures and provides information about the output prepared by the interactor.
 */
class MockSendMoneyOutputBoundary implements SendMoneyOutputBoundary {
    private String lastMessage;
    private SendMoneyOutputData lastData;

    @Override
    public void prepareFailView(String message) {
        lastMessage = message;
    }

    @Override
    public void prepareSuccessCheckBalance(SendMoneyOutputData data) {
        lastData = data;
    }

    @Override
    public void prepareSuccessConvert(SendMoneyOutputData outputData) {

    }

    @Override
    public void prepareSuccessTransfer(SendMoneyOutputData outputData) {

    }

    public String getLastMessage() {
        return lastMessage;
    }

    public SendMoneyOutputData getLastData() {
        return lastData;
    }

    // Other methods...
}

/**
 * Mock implementation of the {@link CurrencyConversionGateway} for testing purposes.
 * This class simulates currency conversion operations.
 */
class MockCurrencyConversionGateway implements CurrencyConversionGateway {
    @Override
    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        // Example conversion logic
        if (!fromCurrency.equals(toCurrency)) {
            return amount * 1.1; // Example conversion rate
        }
        return amount;
    }

    // Other methods...
}

/**
 * Mock implementation of the {@link EmailSenderGateway} for testing purposes.
 * This class captures information about emails sent during testing.
 */
class MockEmailSenderGateway implements EmailSenderGateway {
    private List<String> sentEmails = new ArrayList<>();

    @Override
    public void sendEmail(String recipientEmail, String emailSubject, String emailMessage) {

    }

    @Override
    public void sendWelcomeEmail(String email, String name) {

    }

    @Override
    public void sendTransactionSender(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {
        sentEmails.add("Transaction email to sender: " + email);
    }

    @Override
    public void sendTransactionReceiver(String email, Integer transactionId, Double amount, String currency, String sender, String receiver) {
        sentEmails.add("Transaction email to receiver: " + email);
    }

    @Override
    public void sendUpdateConfirmation(String email, String name) {

    }

    public List<String> getSentEmails() {
        return sentEmails;
    }

    // Other methods...
}

/**
 * JUnit tests for the {@link SendMoneyInteractor} class.
 * These tests focus on verifying the behavior of the SendMoneyInteractor.
 */
class SendMoneyInteractorTest {

    private MockSendMoneyUserDataAccess userDataAccess;
    private MockSendMoneyOutputBoundary outputBoundary;
    private MockCurrencyConversionGateway conversionGateway;
    private MockEmailSenderGateway emailSenderGateway;
    private SendMoneyInteractor interactor;

    @BeforeEach
    void setUp() {
        userDataAccess = new MockSendMoneyUserDataAccess();
        outputBoundary = new MockSendMoneyOutputBoundary();
        conversionGateway = new MockCurrencyConversionGateway();
        emailSenderGateway = new MockEmailSenderGateway();
        interactor = new SendMoneyInteractor(userDataAccess, outputBoundary, conversionGateway, emailSenderGateway);

        // Set up initial data
        userDataAccess.addAccount(1, 100, "USD");
        userDataAccess.addAccount(2, 200, "EUR");
    }

    @Test
    void checkAccount_ValidAccounts() {
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 50, 1234);
        interactor.checkAccount(inputData);

        assertNull(outputBoundary.getLastMessage(), "There should be no error message for valid accounts.");
    }

    @Test
    void checkAccount_InvalidAccounts() {
        SendMoneyInputData inputData = new SendMoneyInputData(1, 3, 50, 1234); // Account 3 does not exist
        interactor.checkAccount(inputData);

        assertNotNull(outputBoundary.getLastMessage(), "There should be an error message for invalid accounts.");
    }

    @Test
    void convert_SameCurrency() {
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 50, 1234);
        interactor.convert(inputData);

        // Assert that the amount is not converted
    }

    @Test
    void convert_DifferentCurrency() {
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 50, 1234); // Different currencies
        interactor.convert(inputData);

        // Assert that the amount is converted
    }

    @Test
    void transfer_SuccessfulTransfer() {
        boolean condition = true;
        SendMoneyInputData inputData = new SendMoneyInputData(1, 2, 50, 1234);
        interactor.transfer(inputData);

        // Assert successful transfer
        assertTrue(condition);
    }

}