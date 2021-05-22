package virtualhost;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Connection {
    public static void main(String... args) {
        // System.setProperty("jdk.httpclient.allowRestrictedHeaders", "host");

        try {
            HttpRequest request = HttpRequest
                    .newBuilder(URI.create("http://localhost"))
                    .setHeader("Host", "test2.co.jp")
                    .build();

            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
