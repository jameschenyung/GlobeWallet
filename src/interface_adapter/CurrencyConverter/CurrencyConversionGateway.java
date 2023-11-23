package interface_adapter.CurrencyConverter;

public interface CurrencyConversionGateway {
    double convertCurrency(String fromCurrency, String toCurrency, double amount);
}
