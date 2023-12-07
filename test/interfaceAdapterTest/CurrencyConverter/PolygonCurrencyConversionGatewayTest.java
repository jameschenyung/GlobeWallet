package interfaceAdapterTest.CurrencyConverter;

import interface_adapter.CurrencyConverter.PolygonCurrencyConversionGateway;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the currency conversion API
 */

class PolygonCurrencyConversionGatewayTest {
    /**
     * This is a unit test that handles the currency conversion between USD and EUR.
     * In this example, we convert 25 US dollars, however you can insert whatever currency
     * you want to convert from or convert to and its amount.
     * You can change the values in assert equals to its correct conversion from the internet
     * to see if this function is accurate.
     * Delta manages the error bounds as required.
     */
    @Test
    void convertCurrency_SuccessfulConversion() {
        // Create an instance of the class under test
        PolygonCurrencyConversionGateway gateway = new PolygonCurrencyConversionGateway();

        // Perform the test with controlled inputs
        double convertedAmount = gateway.convertCurrency("USD", "EUR", 25.0);

        // Verify the result
        assertEquals(23.07, convertedAmount, 0.1); // Adjust the delta based on your requirements
    }

    /**
     * Here we run a failed unit test to see if it returns -1 and accurately fails
     * the unit test.
     */
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

