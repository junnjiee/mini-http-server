import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses the HTTP Request from buffer to string. This is a very simple and
 * naive implementation.
 * <p>
 * Possible improvements:
 * <li>- Instead of returning a string, what I think would be better is to
 * create key value map of the request metadata
 **/
public class HttpRequestParser {
    public static List<String> parse(BufferedReader bufferedInput) throws IOException {
        List<String> requestParams = new ArrayList<String>();
        String line;

        while ((line = bufferedInput.readLine()) != null && !line.isEmpty()) {
            requestParams.add(line);
        }

        System.out.println("\nLOG REQUEST:");
        System.out.println(String.join("\n", requestParams));

        return requestParams;
    }
}