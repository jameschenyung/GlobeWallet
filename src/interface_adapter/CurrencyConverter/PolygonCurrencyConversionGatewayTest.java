package interface_adapter;

import interface_adapter.CurrencyConverter.PolygonCurrencyConversionGateway;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolygonCurrencyConversionGatewayTest {

    @Test
    void convertCurrency_SuccessfulConversion() {
        // Create an instance of the class under test
        PolygonCurrencyConversionGateway gateway = new PolygonCurrencyConversionGateway();

        // Perform the test with controlled inputs
        double convertedAmount = gateway.convertCurrency("USD", "EUR", 25.0);

        // Verify the result
        assertEquals(22.8, convertedAmount, 0.1); // Adjust the delta based on your requirements
    }

    @Test
    void convertCurrency_ApiError() {
        // Create an instance of the class under test
        PolygonCurrencyConversionGateway gateway = new PolygonCurrencyConversionGateway();

        // Perform the test with controlled inputs that simulate an error
        double convertedAmount = gateway.convertCurrency("USA", "EUR", 25.0);

        // Verify the result for an error scenario
        assertEquals(-1.0, convertedAmount, 0.001); // Adjust the delta based on your requirements
    }
}

