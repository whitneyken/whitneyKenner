import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {


        ServerSocket servSock = new ServerSocket(8080);


        while (true) {
            new Socket();
            Socket clientSocket;
            clientSocket = servSock.accept();
            Thread thread = new Thread(new MyRunnable(clientSocket));
            thread.start();

        }

    }
}
