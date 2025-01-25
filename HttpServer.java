import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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

                BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

                // get the full HTTP request as a string
                List<String> requestList = new ArrayList<String>();
                String line;
                while ((line = inputBuffer.readLine()) != null && !line.isEmpty()) {
                    requestList.add(line);
                }
                String request = String.join("\n", requestList);
                System.out.println("REQUEST:");
                System.out.println(request);

                // parse the request
                String[] requestTypeList = requestList.get(0).split(" ");
                String method = requestTypeList[0];
                String route = requestTypeList[1];

                if (method == "GET") {
                    if (route == "/") {
                        
                    }
                }

                // send back something
                // Prepare a simple HTTP response
                String httpResponse = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<html><body><h1>Hello, World!</h1></body></html>";
                outputWriter.println(httpResponse);
                clientSocket.close();
            }

        } catch (IOException i) {
            System.err.println(i);
            return;
        }

    }
}
