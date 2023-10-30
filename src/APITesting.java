import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APITesting {

    private static final String API_URL = "https://api.polygon.io/v1/conversion/AUD/USD?amount=100&precision=2&apiKey=VVwYnnZxhA5RY3nC6Uc0BQrM5trKZWtz";
    private static final String API_KEY = "VVwYnnZxhA5RY3nC6Uc0BQrM5trKZWtz";

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("apikey", API_KEY)
                .header("accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
