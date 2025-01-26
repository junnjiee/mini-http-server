import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Handles HTTP requests and returns a HTTP response. Currently only supports
 * {@code GET} requests, but I written this in a way that (hopefully) is modular
 * enough to easily implement {@code POST}, {@code PUT} and {@code DELETE}.
 * <p>
 * Error handling:
 * <li>- 404 error is returned if resource is not found.
 * <li>- 500 error is returned for Java related errors (e.g.
 * {@code IOException})
 **/
public class HttpRequestHandler {
    public static String handle(List<String> requestParams) throws InvalidHttpRequestException {
        String[] requestHeader = requestParams.get(0).split(" ");
        String route = requestHeader[1];

        try {
            // throws IllegalArgumentException that is optional to handle
            HttpMethodsEnum httpMethod = HttpMethodsEnum.valueOf(requestHeader[0]);

            switch (httpMethod) {
                case HttpMethodsEnum.GET:
                    Map<String, Supplier<String>> routes = GETRoutes.routes();

                    if (routes.containsKey(route)) {
                        return routes.get(route).get();
                    }
                    break;
                default:
                    throw new InvalidHttpRequestException("Server currently only supports GET requests");
            }

            return GETRoutes.error_404();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new InvalidHttpRequestException("HTTP method does not include GET, PUT, POST, DELETE");
        }
    }
}
