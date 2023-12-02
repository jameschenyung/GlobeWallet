package interface_adapter.CurrencyConverter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import interface_adapter.CurrencyConverter.CurrencyConversionGateway;
import org.json.JSONObject;

public class PolygonCurrencyConversionGateway implements CurrencyConversionGateway {
    private static final String POLYGON_API_BASE_URL = "https://api.polygon.io/v1/conversion";
    private static final String API_KEY = "VVwYnnZxhA5RY3nC6Uc0BQrM5trKZWtz"; // Replace with your actual API key

    @Override
    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        String str_amount = Double.toString(amount);
            // Build the URL for the Polygon API
            //String apiUrl = String.format("%s/%s/%s?amount=%f&apiKey=%s",
            //        POLYGON_API_BASE_URL, fromCurrency, toCurrency, amount, API_KEY);

        String API_URL = POLYGON_API_BASE_URL + "/" + fromCurrency + "/" + toCurrency + "?" + "amount="
                + str_amount + "&precision=2&apiKey=" + API_KEY;

            // Open a connection to the API
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("apikey", API_KEY)
                .header("accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());

            // Log the response for debugging
            System.out.println("API Response: " + jsonObject.toString());

            if (jsonObject.has("converted")) {
                return jsonObject.getDouble("converted");
            } else {
                // Handle the case where the key is missing or different
                System.out.println("Key 'converted' not found in JSON response.");
                // You might want to check for other keys or error messages here
                return -1.0; // Or handle this situation differently
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0; // or throw an exception
        }
    }
}
