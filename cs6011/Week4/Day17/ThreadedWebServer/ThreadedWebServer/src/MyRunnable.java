import java.io.IOException;
import java.net.Socket;

public class MyRunnable implements Runnable {
    Socket clientSocket;

    MyRunnable(Socket Sock) {
        clientSocket = Sock;
    }

    @Override
    public void run() {
        HandleRequest HReq = new HandleRequest();
        HandleResponse HResp = new HandleResponse();
        CloseClient CC = new CloseClient();
        System.out.print("Waiting for client...");
        try {
            String fileName = HReq.handleRequest(clientSocket);
            HResp.handleResponse(clientSocket, fileName);
            CC.closeClient(clientSocket);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
