import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class WebSocketHandler {
    public Socket clientSocket_;
    public DataInputStream dis_;

    public String roomName_;
    Room room;
    boolean toLeave = false;

    public void handleWebsocket(Socket clientSocket) throws IOException {
        clientSocket_ = clientSocket;
        dis_ = new DataInputStream(clientSocket.getInputStream());
        decodeStream();
    }

    public void decodeStream() throws IOException {
        System.out.println("decodeStream");
        while (true) {
            System.out.println("in while(true)");
            byte byte0 = dis_.readByte();
            System.out.println("Byte 0 is: " + byte0);
            System.out.println("got byte0");
            int numLengthBytes = 2;
            byte opcode = (byte) (byte0 & 0x0F);
            byte fin = (byte) (byte0 & 0xF0);
            byte byte1 = dis_.readByte();
            boolean isMasked = ((byte1 & 0x80) != 0);
            long payloadLength = (byte1 & 0x7F);
            if (payloadLength == 126) {
                numLengthBytes = 4;
                payloadLength = dis_.readShort();
            } else if (payloadLength == 127) {
                payloadLength = dis_.readLong();
                numLengthBytes = 10;
            }
            byte[] encodedBytes = new byte[(int) payloadLength];
            byte[] decodedBytes = new byte[(int) payloadLength];
            byte[] maskArray = new byte[4];
            if (isMasked) {
                for (int i = 0; i < 4; i++) {
                    maskArray[i] = dis_.readByte();
                }
            }
            for (int k = 0; k < payloadLength; k++) {
                encodedBytes[k] = dis_.readByte();
            }
            for (int j = 0; j < payloadLength; j++) {
                decodedBytes[j] = (byte) (encodedBytes[j] ^ maskArray[j % 4]);
            }
            String payload = new String(decodedBytes);
            System.out.println(payload);

            //handlePayload();

            //Now I will handle the response to the client within here
            String fakeJsonToSend = getFakeJson(payload, opcode);
            handleWSResponse(fakeJsonToSend, opcode);


        }

    }

    private String getFakeJson(String payload, byte opcode) {
        String typeOfPayload = payload.split(" ", 2)[0];
        System.out.println("Type of payload: " + typeOfPayload);
        String fakeJson = "";
        String userName = "";
        String chatRoom = "";
        String timeStamp = new SimpleDateFormat("HH:mm").format(new java.util.Date());
        if (!typeOfPayload.equals("join") && !typeOfPayload.equals("leave")) {
            typeOfPayload = "message";
            userName = payload.split(" ", 2)[0];
            String payloadMessage = payload.split(" ", 2)[1];
            System.out.println("The payload message is: " + payloadMessage);
            fakeJson += "{ " + "\"" + "type" + "\"" + " : " + "\"" + typeOfPayload + "\", " +
                    "\"" + "user" + "\"" + " : " + "\"" + userName + "\", " + "\"" + "room" + "\""
                    + " : " + "\"" + roomName_ + "\", " + "\"" + "timeStamp" + "\"" + " : " + "\"" + timeStamp + "\", " +
                    "\"" + "message" + "\"" + " : " + "\"" + payloadMessage + "\"" +
                    " }";
            System.out.println(fakeJson);
        } else {
            userName = payload.split(" ", 3)[1];
            chatRoom = payload.split(" ", 3)[2];
            roomName_ = chatRoom;
            fakeJson += "{ " + "\"" + "type" + "\"" + " : " + "\"" + typeOfPayload + "\", " +
                    "\"" + "room" + "\"" + " : " + "\"" + roomName_ + "\", " + "\"" + "timeStamp" + "\"" + " : " + "\"" +
                    timeStamp + "\", " + "\"" + "user" + "\"" + " : " + "\"" + userName + "\"" + " }";
            System.out.println(fakeJson);
        }
        if (typeOfPayload.equals("leave")){
            toLeave = true;
        }
        return fakeJson;
    }

    private void handleWSResponse(String fakeJsonToSend, byte opcode) throws IOException {
        room = Room.getRoom(roomName_);
        System.out.println("The returned room name is:" + room.roomName_);
        room.addUserToRoom(clientSocket_);
        room.sendMessageToRoom(fakeJsonToSend, opcode);
        if (toLeave){
            room.removeUserFromRoom(clientSocket_);
            toLeave =false;
        }



    }
}
