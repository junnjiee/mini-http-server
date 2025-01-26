import java.io.IOException;
import java.util.Map;
import static java.util.Map.entry;
import java.util.function.Supplier;

/**
 * This class contains all route methods for {@code GET} requests.
 * <p>
 * Possible improvements:
 * <li>- Handling of URL params
 **/
public final class GETRoutes extends Routes {
    private static Map<String, Supplier<String>> routes = Map.ofEntries(
            entry("/", GETRoutes::index),
            entry("/hello", GETRoutes::helloJson));

    public static Map<String, Supplier<String>> routes() {
        return routes;
    }

    private static String index() {
        int status = 200;

        try {
            String html = htmlFileReader("index.html");
            String response = httpVersion + " " + String.valueOf(status) + " " + statusTitles.get(status);
            String contentType = "Content-Type: text/html; charset=UTF-8";

            return response + "\n" + contentType + "\n\n" + html;

        } catch (IOException e) {
            e.printStackTrace();
            return error_500();
        }
    }

    private static String helloJson() {
        int status = 200;
        String response = httpVersion + " " + String.valueOf(status) + " " + statusTitles.get(status);
        String contentType = "Content-Type: application/json;";
        String jsonString = "{\"message\": \"hello world!\"}";

        return response + "\n" + contentType + "\n\n" + jsonString;
    }

}
