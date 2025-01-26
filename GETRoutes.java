import java.io.IOException;
import java.util.Map;
import static java.util.Map.entry;

/**
 * This class contains all route methods for {@code GET} requests.
 * <p>
 * Possible improvements:
 * <li>- Handling of URL params
 * <li>- The better solution for matching route names to its method is to build
 * some form of a map, instead of matching by method name itself
 **/
public final class GETRoutes extends Routes {
    private static Map<String, Supplier<String>> routes = Map.ofEntries(
            entry("/", GETRoutes::index));

    public static Map<String, Supplier<String>> routes() {
        return routes;
    }

    private static String index() {
        int status = 200;

        try {
            String html = htmlFileReader("index.html");
            String response = httpVersion + String.valueOf(status) + statusTitles.get(status);
            String contentType = "Content-Type: text/html; charset=UTF-8";

            return response + "\n" + contentType + "\n\n" + html;

        } catch (IOException e) {
            e.printStackTrace();
            return error_500();
        }
    }

}
