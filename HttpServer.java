import java.io.IOException;
import java.net.ServerSocket;

/**
 * Implements a simplified HTTP server with plain Java. The goal of building
 * this is to learn how a HTTP server works and learn Java at the same time.
 * <p>
 * IMPORTANT: This may not be the correct way of building a HTTP server, it is
 * merely just my attempt at building one that works.
 **/
public class HttpServer {
    private ServerSocket serverSocket;
    private int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);

            while (true) {
                // method blocks until there is a connection
                new ConnectionHandler(serverSocket.accept()).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
