import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    String roomName_;

    ArrayList<Socket> connectedSockets = new ArrayList<>();

    static HashMap<String, Room> allTheRooms = new HashMap<>();


    private Room(String name) {
        roomName_ = name;
    }

    public synchronized static Room getRoom(String roomName) {
        System.out.println("Inside of getRoom method");
        Room room;
        if (allTheRooms.containsKey(roomName)) {
            room = allTheRooms.get(roomName);
        } else {
            room = new Room(roomName);
            allTheRooms.put(roomName, room);
        }
        System.out.println("\t room name is: " + roomName);
        return room;

    }

    //need add user or add client socket method for room, needs to be synchronized
    public synchronized void addUserToRoom(Socket clientSocket) {
        if (!connectedSockets.contains(clientSocket)) {
            connectedSockets.add(clientSocket);
        }
        System.out.println("all the users in this room include: ");
            System.out.println("num of connected sockets: " + connectedSockets.size());

    }

    //send message to all clients in room need to use synchronized to prevent multiple threads from calling the same method at the same time
    public synchronized void sendMessageToRoom(String fakeJsonToSend, byte opcode) throws IOException {
        for (Socket s : connectedSockets) {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            int lengthOfFakeJson = fakeJsonToSend.length();
            //ArrayList<Byte> byteArrayListToSend = new ArrayList<>();
            if (opcode == 0x8) {
                //do something with the rooms idk lol
                removeUserFromRoom(s);
                System.out.println("had an opcode that indicates leaving ");
            }
            dos.write(0x81);
            //change to regular output stream because then we can do output.writebyte, output.writeshort, output.writelong, etc
            if (lengthOfFakeJson < 126) {
                dos.write(lengthOfFakeJson & 0x7f);
            } else if (lengthOfFakeJson < Math.pow(2, 16)) {
                dos.write(126);
                dos.writeShort(lengthOfFakeJson);
            } else {
                dos.write(127);
                dos.writeLong(lengthOfFakeJson);
            }
            byte[] fakeJsonAsByteArray = fakeJsonToSend.getBytes();

            dos.write(fakeJsonAsByteArray);
            dos.flush();
        }
        //not quite sure how to do this yet
        //for each socket in this room
        //send message
    }

    //A synchronized method to remove a client
    public synchronized void removeUserFromRoom(Socket clientSocket) {
        connectedSockets.remove(clientSocket);
    }


    //A list of all the clients?? somehow clients connected to each room
}