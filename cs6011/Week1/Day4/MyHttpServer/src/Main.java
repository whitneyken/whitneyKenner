import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        int port = 8080;
        Server server = new Server(port);
        //Socket clientSocket;

        while (true) {
            try {
                System.out.print("Waiting for client...");
                server.handleRequest();
                server.handleResponse();
                server.closeClient();
            } catch (Exception e) {
                // do nothing but print error since we want the server to continue to run...
                System.out.println("Got here, shouldn't have");
                System.out.println(e);
            }

        }
    }
}