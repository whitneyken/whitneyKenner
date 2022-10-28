import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HandleRequest {
    String fileName;
    Boolean isWebSocket = false;
    String webSocketKey;

    DataInputStream dis;
    DataOutputStream dos;


    public String handleHTTPRequest(Socket clientSocket) throws IOException, NoSuchAlgorithmException {

        System.out.println("Accepted");
        System.out.println("-------------------------------------------");

        // Parsing HTTP request
        // use a scanner to read the info from the input stream
        Scanner inputScanner = new Scanner(clientSocket.getInputStream());
        HashMap<String, String> headerHash = new HashMap<>();
        System.out.println("\nabout to read first line\n");
        String line = inputScanner.nextLine();
        System.out.println("\tHTTP REQUEST:\n\t\t" + line);

        //Then we need to parse through the first line and determine what file is wanted
        String[] arrOfFirstLine = line.split(" ");
        fileName = arrOfFirstLine[1];

        //after we have determined the file name, we need to check if the file exists
        if (fileName.equals("/")) {
            fileName = "index.html";
        } else {
            fileName = fileName.substring(1);
        }


        try {//figuring our how to store the rest of the http header in a hashmap?
            while (!line.isBlank()) {
                line = inputScanner.nextLine();

                String[] arrForHashVals = line.split(": ");
                if (!line.isBlank()) {
                    headerHash.put(arrForHashVals[0], arrForHashVals[1]);
                    System.out.println("\t" + arrForHashVals[0] + arrForHashVals[1]);
                }


            }

            System.out.println(headerHash);
        } catch (NoSuchElementException e) {
            //do nothing
        }
        for (String i : headerHash.keySet()) {
            System.out.println("key: " + i + " value: " + headerHash.get(i));
        }

        //now we need to find out connection  value is upgrade
        if (headerHash.containsKey("Upgrade") && headerHash.containsValue("websocket")) {
            System.out.println("Websocket upgrade is contained");
            isWebSocket = true;
            //1st value returns the websocket key, magic key is equal to 2nd value is magic key
            webSocketKey = headerHash.get("Sec-WebSocket-Key") + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
            System.out.println("webSocketKey" + webSocketKey);


            System.out.println("got dis");



        }
        return fileName;
    }

    public void handleWSRequest() {

    }

    public String getWebSocketKey() {
        return webSocketKey;
    }

    public Boolean getIsWebSocket() {
        return isWebSocket;
    }

}

