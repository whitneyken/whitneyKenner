import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        int port = 8080;
        while (true) {
            try {
                Server server = new Server(port);
                server.handleRequest();
                server.handleResponse();
                server.closeShit();
            }
            catch(IOException e){
                // do nothing, just continue running server for the next client
            }

        }
    }
}