import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class ConnectionHandler implements Runnable {
    Socket clientSocket;
    boolean isWebSocket;

    ConnectionHandler(Socket Sock) {
        clientSocket = Sock;
    }

    @Override
    public void run() {
        HandleRequest HReq = new HandleRequest();
        HandleResponse HResp = new HandleResponse();
        CloseClient CC = new CloseClient();
        System.out.print("Waiting for client...");
        try {
            String fileName = HReq.handleHTTPRequest(clientSocket);
            // do handshake thing
            HResp.handleResponse(clientSocket, fileName, HReq);
            isWebSocket = HReq.getIsWebSocket();
            if (isWebSocket) {
                WebSocketHandler WSH = new WebSocketHandler();
                WSH.handleWebsocket(clientSocket);
            }
            CC.closeClient(clientSocket);
        } catch (InterruptedException | IOException e) {
            //throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}




