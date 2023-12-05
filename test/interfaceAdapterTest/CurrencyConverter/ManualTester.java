package interfaceAdapterTest.CurrencyConverter;

import interface_adapter.CurrencyConverter.PolygonCurrencyConversionGateway;

/**
 * This class serves as a manual tester for currency conversion functionality.
 * It uses the PolygonCurrencyConversionGateway to convert a specified amount of money
 * from one currency to another.
 */

public class ManualTester {
    /**
     * The main method to test currency conversion.
     * It sets up the currencies and amount to convert, and then uses the PolygonCurrencyConversionGateway
     * to perform the conversion. The result is printed to the console.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        String convertFrom = "USD";   // Enter currency convert from
        String convertTo = "CAD";   // Enter currency convert to
        double amount = 225.0;   // Enter amount converting from

        PolygonCurrencyConversionGateway gateway = new PolygonCurrencyConversionGateway();

        // Perform the test with controlled inputs
        double convertedAmount = gateway.convertCurrency(convertFrom, convertTo, amount);
        System.out.println(convertedAmount);
    }
}
