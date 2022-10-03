import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        int port = 8080;

        while (true) {
            try {
                Server server = new Server(port);
                server.handleRequest();
                server.handleResponse();
                server.closeClient();
            } catch (Exception e) {
                // do nothing but print error since we want the server to continue to run...
                System.out.println(e.getMessage());
            }
        }
    }
}