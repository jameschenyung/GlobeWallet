package interface_adapter.CurrencyConverter;

/**
 * Interface defining the contract for currency conversion.
 * This interface should be implemented by classes that provide functionality
 * to convert an amount from one currency to another.
 */
public interface CurrencyConversionGateway {

    /**
     * Converts an amount from one currency to another.
     * Implementing this method requires handling the logic for converting
     * a specified amount of money from a source currency to a target currency.
     *
     * @param fromCurrency The currency code to convert from (e.g., "USD").
     * @param toCurrency   The currency code to convert to (e.g., "EUR").
     * @param amount       The amount of money to convert.
     * @return The converted amount in the target currency.
     */
    double convertCurrency(String fromCurrency, String toCurrency, double amount);
}
