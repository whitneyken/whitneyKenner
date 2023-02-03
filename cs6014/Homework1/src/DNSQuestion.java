import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class DNSQuestion {
    private short QTYPE;
    private short QCLASS;
    private ArrayList<String> domainRequest;


    //this method decodes the passed in question. It takes in a byte array input stream and the DNSmessage containing the question and returns a DNSQuestion
    static DNSQuestion decodeQuestion(ByteArrayInputStream inputStream, DNSMessage message)  {
        DNSQuestion question = new DNSQuestion();
        question.domainRequest = new ArrayList<>();
        question.domainRequest = message.readDomainName(inputStream);
        byte qtypeByte1 = (byte) inputStream.read();
        byte qtypeByte2 = (byte) inputStream.read();
        question.QTYPE = question.twoBytesToShort(qtypeByte1, qtypeByte2);
        byte qclassByte1 = (byte) inputStream.read();
        byte qclassByte2 = (byte) inputStream.read();
        question.QCLASS = question.twoBytesToShort(qclassByte1, qclassByte2);
        return question;
    }

    //This method takes in a byte array output stream and hashmap of domain locations and returns nothing
    void writeBytes(ByteArrayOutputStream outputStream, HashMap<String,Integer> domainNameLocations){
        //this method is called to write and possibly compress the domain name
        DNSMessage.writeDomainName(outputStream, domainNameLocations, this.domainRequest);
        short qType = this.QTYPE;
        short qWord = this.QCLASS;
        outputStream.write((byte) ((qType >> 8) & 0xFF));
        outputStream.write((byte) (qType & 0xFF));
        outputStream.write((byte) ((qWord >> 8 ) & 0xFF));
        outputStream.write((byte) (qWord & 0xFF));
    }

    short twoBytesToShort(byte firstByte, byte secondByte) {
        return (short) ((short) ((firstByte & 0xFF) << 8) | secondByte & 0xFF);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DNSQuestion that = (DNSQuestion) o;
        return QTYPE == that.QTYPE && QCLASS == that.QCLASS && Objects.equals(domainRequest, that.domainRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(QTYPE, QCLASS, domainRequest);
    }
}
