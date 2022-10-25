import java.io.IOException;
import java.net.Socket;

public class CloseClient {

    public void closeClient(Socket clientSocket) throws IOException {
        clientSocket.close();
        if (!clientSocket.isClosed()) {
            throw new IOException("Failure to close client socket ");
        }
    }
}
