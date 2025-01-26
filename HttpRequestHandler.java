import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
                    if (route == "") {
                        route = "index";
                    }
                    GETRoutes httpMethodRoutes = new GETRoutes();
                    java.lang.reflect.Method routeFn = httpMethodRoutes.getClass().getMethod(route);
                    return (String) routeFn.invoke(httpMethodRoutes);
                default:
                    throw new InvalidHttpRequestException("Server currently only supports GET requests");
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // method could not be found, in this case return a 404 error
            e.printStackTrace();
            return GETRoutes.error_404();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new InvalidHttpRequestException("HTTP method does not include GET, PUT, POST, DELETE");
        }
    }
}
