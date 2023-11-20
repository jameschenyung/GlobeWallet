package interface_adapter;

public interface CurrencyConversionGateway {
    double convertCurrency(String fromCurrency, String toCurrency, double amount);
}
