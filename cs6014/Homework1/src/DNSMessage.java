import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DNSMessage {
    private DNSHeader header;
    private ArrayList<DNSQuestion> questions;
    private ArrayList<DNSRecord> answerRRs;
    private ArrayList<DNSRecord> serverRRS;
    private ArrayList<DNSRecord> additionalRRs;
    private byte[] fullMessage;

    private DNSMessage() {
        questions = new ArrayList<>();
        answerRRs = new ArrayList<>();
        serverRRS = new ArrayList<>();
        additionalRRs = new ArrayList<>();
    }

    //this method takes in a byte array and runs the decoding of the message receives and returns a DNSMessage
    static DNSMessage decodeMessage(byte[] bytes) throws IOException {
        //create the message that will contain all the message data
        DNSMessage message = new DNSMessage();
        message.fullMessage = bytes;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        //decode header
        message.header = DNSHeader.decodeHeader(inputStream);
        //decode questions
        for (int i = 0; i < message.header.getNumQuestions(); i++) {
            message.questions.add(DNSQuestion.decodeQuestion(inputStream, message));
        }
        //decode all RRs
        for (int i = 0; i < message.header.getNumAnswerRRs(); i++) {
            message.answerRRs.add(DNSRecord.decodeRecord(inputStream, message));
        }
        for (int j = 0; j < message.header.getNumNameServerRRs(); j++) {
            message.serverRRS.add(DNSRecord.decodeRecord(inputStream, message));
        }
        for (int k = 0; k < message.header.getNumAdditionalRRs(); k++) {
            message.additionalRRs.add(DNSRecord.decodeRecord(inputStream, message));
        }
        return message;
    }

    //this method reads the domain name in. takes in a byte array input stream and returns an arraylist of strings containing the domain name pieces
    ArrayList<String> readDomainName(ByteArrayInputStream inputStream) {
        int nameLength;
        ArrayList<String> domainName = new ArrayList<>();

        while (true) {
            nameLength = inputStream.read();
            //if the next byte read in is a 0, the end of the domain name has been reached and we break
            if (nameLength == 0) {
                break;
            } else {
                StringBuilder domainComponentString = new StringBuilder();
                for (int i = 0; i < nameLength; i++) {
                    byte currentChar = (byte) inputStream.read();
                    domainComponentString.append((char) currentChar);
                }
                domainName.add(String.valueOf(domainComponentString));
            }
        }
        return domainName;
    }

    //this method is for reading a compressed domain name. it takes in an int of the index of the domain name location in the original byte array and
    //returns an arraylist of strings of the pieces of the name
    ArrayList<String> readDomainName(int firstByte) {
        ArrayList<String> domainName = new ArrayList<>();
        int nameLength;
        int byteIndex = firstByte;
        while (true) {
            nameLength = fullMessage[byteIndex++];
            if (nameLength == 0) {
                return domainName;
            } else {
                StringBuilder domainComponentString = new StringBuilder();
                for (int i = 0; i < nameLength; i++) {
                    char c = (char) fullMessage[byteIndex++];
                    domainComponentString.append(c);

                }
                domainName.add(String.valueOf(domainComponentString));
            }
        }
    }

    public ArrayList<DNSQuestion> getQuestions() {
        return questions;
    }

    public DNSRecord getFirstAnswer() {
        //if there are no answers
        if (this.answerRRs.size() == 0){
            return null;
        }else {
            return this.answerRRs.get(0);
        }
    }

    //this method handles building the response for the client when the answer is stored in cache. it takes in the DNSMessage request and the DNSRecord answer
    //returns a DNSMessage of the response
    static DNSMessage buildResponse(DNSMessage request, DNSRecord answer) {
        //get byte array from header
        DNSMessage response = new DNSMessage();
        response.header = DNSHeader.buildHeaderForResponse(request);
        response.questions = request.getQuestions();
        response.answerRRs.add(answer);
        response.serverRRS = request.getServerRRS();
        response.additionalRRs = request.getAdditionalRRs();

        return response;
    }

    //this method converts the entire DNSMessage to bytes to be returned to the client and returns a byte array
    byte[] toBytes() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HashMap<String, Integer> domainLocations = new HashMap<>();
        outputStream.write(this.getHeader().getAllBytes());
        this.questions.get(0).writeBytes(outputStream, domainLocations);
        this.answerRRs.get(0).writeBytes(outputStream, domainLocations);
        for (int k = 0; k < this.serverRRS.size(); k++) {
            this.answerRRs.get(0).writeBytes(outputStream, domainLocations);
        }
        for (int i = 0; i < this.additionalRRs.size(); i++) {
            this.answerRRs.get(0).writeBytes(outputStream, domainLocations);
        }
        return outputStream.toByteArray();
    }

    //this methos is for writing the domain name. it takes in an output stream, a hashmap of the domain locations and an ararylist of the domain pieces
    static void writeDomainName(ByteArrayOutputStream outputStream, HashMap<String, Integer> domainLocations, ArrayList<String> domainPieces) {
        String domainName = joinDomainName(domainPieces);
        //if the domain is already in the hashmap, it should now be compressed
        if (domainLocations.containsKey(domainName)) {
            byte compressionByte1 = (byte) 0xC0;
            int offsetInt = domainLocations.get(domainName);
            byte offSetByte1 = (byte) ((offsetInt >> 8) & 0x3F);
            offSetByte1 = (byte) (offSetByte1 | compressionByte1);
            byte offSetByte2 = (byte) (offsetInt & 0xFF);
            outputStream.write(offSetByte1);
            outputStream.write(offSetByte2);
            //otherwise write it out
        } else {
            domainLocations.put(domainName, outputStream.size());
            for (int i = 0; i < domainPieces.size(); i++) {
                String currentString = domainPieces.get(i);
                outputStream.write((byte) currentString.length());
                for (int j = 0; j < currentString.length(); j++) {
                    outputStream.write((byte) currentString.charAt(j));
                }
            }
            outputStream.write((byte) 0x00);
        }
    }
    //this method is for joining the domain name pieces into a full string
    static String joinDomainName(ArrayList<String> domainPieces) {
        StringBuilder domainName = new StringBuilder();
        for (int i = 0; i < domainPieces.size(); i++) {
            domainName.append(domainPieces.get(i));
            if (i < domainPieces.size() - 1) {
                domainName.append(".");
            }
        }
        return String.valueOf(domainName);
    }

    DNSHeader getHeader() {
        return header;
    }

    ArrayList<DNSRecord> getServerRRS() {
        return serverRRS;
    }

    ArrayList<DNSRecord> getAdditionalRRs() {
        return additionalRRs;
    }

}
