import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APITesting {

    private static final String API_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/sale/detail?address1=468%20SEQUOIA%20DR&address2=SMYRNA%2C%20DE";
    private static final String API_KEY = "5b395326789c91adb16a62e1ca7b81bc";

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
