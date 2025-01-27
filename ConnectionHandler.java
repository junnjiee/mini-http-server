import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Creates a new thread that handles the new connection. This ensures that the
 * server can handle multiple concurrent requests.
 **/
public class ConnectionHandler implements Runnable {
    private Socket clientSocket;

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            List<String> requestParams = HttpRequestParser.parse(bufferedInput);
            String response = HttpRequestHandler.handle(requestParams);

            outputWriter.println(response);
            clientSocket.close();
            // block the current thread to test concurrency
            Thread.sleep(10000);

        } catch (IOException | InvalidHttpRequestException | InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }
}
