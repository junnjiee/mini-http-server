import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

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
                Socket clientSocket = serverSocket.accept();
                BufferedReader bufferedInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

                List<String> requestParams = HttpRequestParser.parse(bufferedInput);
                String response = HttpRequestHandler.handle(requestParams);

                outputWriter.println(response);
                clientSocket.close();
            }

        } catch (IOException | InvalidHttpRequestException e) {
            e.printStackTrace();
            return;
        }
    }
}
