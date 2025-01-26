import java.util.Map;
import static java.util.Map.entry;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Abstract class for each method type ({@code GET}, {@code POST}, {@code PUT},
 * {@code DELETE}) to implement their own custom routes. error handling routes
 * for status 404 and 500 have been provided.
 * <p>
 * Note: This server does not handle nested routes
 * <p>
 * Possible improvements:
 * <li>- Make building of responses more modular and standardized, maybe with a
 * method
 * <li>- Use a better string concatenation pattern
 * <li>- dynamic way to check content type
 * <li>- [Java related] A better way to handle {@code IOException} when using
 * {@code htmlFileReader}, if not each route needs to manually return status 500
 * response
 **/
public abstract class Routes {
    protected static String httpVersion = "HTTP/1.1";
    protected static String htmlDir = "./html";

    /**
     * Only implements 200, 404 and 500 statuses for simplicity
     **/
    protected static Map<Integer, String> statusTitles = Map.ofEntries(
            entry(200, "OK"),
            entry(404, "Not Found"),
            entry(500, "Internal Server Error"));

    protected static String htmlFileReader(String fileName) throws IOException {
        String htmlContent = Files.readString(Path.of(htmlDir, fileName));
        return htmlContent;
    }

    /**
     * Built in response for status code 404 that can be overridden
     **/
    public static String error_404() {
        String response = httpVersion + "404" + statusTitles.get(404);
        String contentType = "Content-Type: text/html; charset=UTF-8";
        return response + "\n" + contentType + "\n\n" + "<html><body><h1>404 Not Found</h1></body></html>";
    }

    /**
     * Built in response for status code 500 that can be overridden
     **/
    public static String error_500() {
        String response = httpVersion + "500" + statusTitles.get(500);
        String contentType = "Content-Type: text/html; charset=UTF-8";
        return response + "\n" + contentType + "\n\n" + "<html><body><h1>500 Internal Server Error</h1></body></html>";
    }
}
