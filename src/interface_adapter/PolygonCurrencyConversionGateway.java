package interface_adapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class PolygonCurrencyConversionGateway implements CurrencyConversionGateway {
    private static final String POLYGON_API_BASE_URL = "https://api.polygon.io/v1/conversion";
    private static final String API_KEY = "VVwYnnZxhA5RY3nC6Uc0BQrM5trKZWtz"; // Replace with your actual API key

    @Override
    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        try {
            // Build the URL for the Polygon API
            String apiUrl = String.format("%s/%s/%s?amount=%f&apiKey=%s",
                    POLYGON_API_BASE_URL, fromCurrency, toCurrency, amount, API_KEY);

            // Open a connection to the API
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            // Read the response from the API
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Parse the response and extract the converted amount
            // Note: This part would depend on the actual response format from the API
            double convertedAmount = parseApiResponse(response.toString());

            // Close the connection
            connection.disconnect();

            return convertedAmount;
        } catch (IOException e) {
            // Handle API call error
            e.printStackTrace();
            return -1.0; // or throw an exception
        }
    }

    private double parseApiResponse(String response) {
        // Implement the logic to parse the response and extract the converted amount
        // This would depend on the actual response format from the Polygon API
        // For simplicity, let's assume a JSON response and parse it here
        // Note: You should use a JSON parsing library like Jackson or Gson for a real-world scenario
        // This is just a placeholder, and you need to adapt it based on the actual response structure.
        return 0.0;
    }
}
