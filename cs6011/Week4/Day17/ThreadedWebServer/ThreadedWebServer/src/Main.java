import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {


        ServerSocket servSock = new ServerSocket(8080);
        ArrayList<Socket> connectedSockets = new ArrayList<>();


        while (true) {
            new Socket();
            Socket clientSocket;
            clientSocket = servSock.accept();
            Thread thread = new Thread(new ConnectionHandler(clientSocket));
            connectedSockets.add(clientSocket);
            thread.start();

        }

    }
}
