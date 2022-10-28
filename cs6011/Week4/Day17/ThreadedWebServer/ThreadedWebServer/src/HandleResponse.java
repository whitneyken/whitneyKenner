import netscape.javascript.JSObject;

import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HandleResponse {
    boolean isWebSocket;
    String encryptedKey;
    Socket clientSocket_;

    public void handleResponse(Socket clientSocket, String fileName, HandleRequest HR) throws IOException, InterruptedException, NoSuchAlgorithmException {
        //if the file exists, we should return it
        clientSocket_ = clientSocket;
        OutputStream outputStream = clientSocket_.getOutputStream();

        PrintWriter writer = new PrintWriter(outputStream);

        isWebSocket = HR.getIsWebSocket();
        if (isWebSocket) {
            //This is handling the websocket key and encrypting it to send
            String key = HR.getWebSocketKey();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(key.getBytes());
            byte[] digest = md.digest();
            encryptedKey = Base64.getEncoder().encodeToString(digest);
            System.out.println(encryptedKey);

            //Generating the handshake response
            writer.write("HTTP/1.1 101 Switching Protocols" + "\n");
            writer.write("Upgrade: websocket" + "\n");
            writer.write("Connection: Upgrade" + "\n");
            writer.write("Sec-Websocket-Accept: " + encryptedKey + "\n");
            writer.write("\n");
            writer.flush();



//            HTTP/1.1 101 Switching Protocols
//            Upgrade: websocket
//            Connection: Upgrade
//            Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=
        } else {

            File file = new File(fileName);
            int fileStatus = 200;
            String message = "OK";
            String fileType = "text/" + getFileExtension(file);

            if (!file.exists()) {
                fileStatus = 404;
                System.out.println("\tFile NOT found");
                message = "File NOT found";
            }



            // Build Response
            writer.write("HTTP/1.1 " + fileStatus + " " + message + "\r\n");

            System.out.println("\tGenerating response:");
            System.out.println("\t\tHTTP/1.1 " + fileStatus + " " + message);

            if (fileStatus == 200) {
                //Content type as a variable, so that the css can be pulled up as well
                writer.write("Content-Type: " + fileType + "\r\n");
                writer.write("Content-Size: " + file.length() + "\r\n");
                writer.write("\n");
                writer.flush();
                FileInputStream inputStream = new FileInputStream(file);
                inputStream.transferTo(outputStream);
//            for( int i = 0; i < file.length(); i++ ) {
//                try {
//                    Thread.sleep(10); // Maybe add <- if images are still loading too quickly...
//                }catch (Exception e){
//                    System.out.println("failed to sleep");
//                }
//                outputStream.write( inputStream.read() );
//                outputStream.flush();
//
//            }

                System.out.println("\t\tContent-Type: " + fileType);
                System.out.println("\t\tContent-Size: " + file.length());
            }
            System.out.println("-------------------------------------------");
            writer.flush();
            outputStream.close();
            clientSocket.close();
        }
    }


    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".") + 1;
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }
//    public void sendPayLoad(String payload) throws IOException {
//        OutputStream outputStream = clientSocket_.getOutputStream();
//
//        PrintWriter writer = new PrintWriter(outputStream);
//        writer.write(payload);
//        writer.flush();
//        writer.close();
//        System.out.println("This is the payload from within sendPayLoad: " + payload);
//
//        JSObject response = new JSObject() {
//        }
//    }

}


//{
//        "type"    : "message",
//        "user"    : "theNameOfTheUserWhoSentTheMessage",
//        "room"    : "nameOfRoom",
//        "message" : "the message..."
//        }

//{
//        "type" : <join | leave>,
//        "room" : "nameOfRoom",
//        "user" : "theNameOfTheUserWhoSentTheMessage",
//        }
