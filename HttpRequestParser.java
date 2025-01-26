import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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