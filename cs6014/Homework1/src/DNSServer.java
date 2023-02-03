import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;

public class DNSServer {
    DatagramSocket socket;
    InetAddress responseAddress;
    int responsePort;

    public DNSServer() throws SocketException {
        socket = new DatagramSocket(8069);

    }

    //this method handles the full process of the client request, returns nothing
    public void handleClientRequest() throws IOException {
        byte[] data = new byte[512];
        DatagramPacket packet = new DatagramPacket(data, 512);
        //to continue being able to accept packets
        while (true){
            socket.receive(packet);
            responseAddress = packet.getAddress();
            responsePort = packet.getPort();
            data = packet.getData();
            //handling decoding the message
            DNSMessage message = DNSMessage.decodeMessage(data);
            for(DNSQuestion question: message.getQuestions()){
                // check the cache
                DatagramPacket responsePacket;
                //if it's in cache, build the response message and send it
                if (DNSCache.hasRecord(question)){
                    DNSMessage responseMessage = DNSMessage.buildResponse(message, DNSCache.getRecord(question));
                    byte[] dataToSend = responseMessage.toBytes();
                    responsePacket = new DatagramPacket(dataToSend, dataToSend.length, responseAddress, responsePort);
                    socket.send(responsePacket);

                }else{
                    //otherwise punt the response to google and then return google's DNS response to the client
                    DatagramPacket packetToSendToGoogle = new DatagramPacket(data, data.length, InetAddress.getByName("8.8.8.8"), 53);
                    socket.send(packetToSendToGoogle);
                    socket.receive(packetToSendToGoogle);
                    byte[] dataFromGoogle = packetToSendToGoogle.getData();
                    DNSMessage messageFromGoogle = DNSMessage.decodeMessage(dataFromGoogle);
                    //if Google's response is for an invalid request, don't attempt to store anything in the answer record.
                    //otherwise store the response
                    if (messageFromGoogle.getFirstAnswer() != null) {
                        DNSCache.insert(question, messageFromGoogle.getFirstAnswer());
                    }
                    responsePacket = new DatagramPacket(dataFromGoogle, dataFromGoogle.length, responseAddress, responsePort);
                    socket.send(responsePacket);
                }
            }
        }
    }






    /* This class should open up a UDP socket (DatagramSocket class in Java), and listen for requests.
    When it gets one, it should look at all the questions in the request. If there is a valid answer in
    cache, add that the response, otherwise create another UDP socket to forward the request Google
    (8.8.8.8) and then await their response. Once you've dealt with all the questions, send the response
    back to the client.


Note: dig sends an additional record in the "additionalRecord" fields with a type of 41. You're
supposed to send this record back in the additional record part of your response as well.

Note, that in a real server, the UDP packets you receive could be client requests or google
responses at any time. For our basic testing you can assume that the next UDP packet you get after
forwarding your request to Google will be the response from Google. To be more robust, you can look
at the ID in the request, and keep track of your "in-flight" requests to Google, but you don't need to
do that for the assignment.
*/
}

