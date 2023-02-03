import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DNSServer server = new DNSServer();
        server.handleClientRequest();

    }
}