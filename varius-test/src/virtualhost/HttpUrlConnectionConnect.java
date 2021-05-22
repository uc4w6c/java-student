package virtualhost;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;

public class HttpUrlConnectionConnect {
    public static void main(String[] args) throws IOException {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        URL url = new URL("http://localhost");
        HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
        urlconn.setRequestMethod("GET");
        urlconn.addRequestProperty("Host", "test2.co.jp");

        System.out.println(urlconn.getRequestProperties());

        try (AutoCloseable c = () -> urlconn.disconnect();
             InputStreamReader inReader = new InputStreamReader(urlconn.getInputStream());
             BufferedReader reader = new BufferedReader(inReader);
        ) {
            urlconn.connect();

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
