package interface_adapter.CurrencyConverter;

public class ManualTester {
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
