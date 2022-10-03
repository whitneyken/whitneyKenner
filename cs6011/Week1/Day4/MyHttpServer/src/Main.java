import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        int port = 8080;
        Server server = new Server(port);
        while (true) {
            try {
                server.waitForClient();
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