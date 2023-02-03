import java.io.ByteArrayInputStream;
import java.io.IOException;

public class DNSHeader {
    byte [] allBytes;
    byte qr, opCode, AA, TC, RD, RA, Z, AD, CD, RCODE;

    short id, QDCOUNT, ANCOUNT, NSCOUNT, ARCOUNT;

    //this static method takes in an input stream containing a byte array and returns a DNSHeader
    static DNSHeader decodeHeader(ByteArrayInputStream inputStream) throws IOException {
        DNSHeader header = new DNSHeader();
        header.allBytes = inputStream.readNBytes(12);
        //get ID
        header.id = header.twoBytesToShort(header.allBytes[0], header.allBytes[1]);
        //get QR bit
        header.qr = (byte) ((header.allBytes[2] >> 7) & 0x01);
        //get opcode
        header.opCode = (byte) ((header.allBytes[2] >> 3) & 0x01);
        //get AA
        header.AA = (byte) ((header.allBytes[2] >>2) & 0x01);
        //get TC
        header.TC = (byte) ((header.allBytes[2] >> 1) & 0x01);
        //get RD
        header.RD = (byte) (header.allBytes[2] & 0x01);
        //get RA
        header.RA = (byte) ((byte)(header.allBytes[3] >> 7) & 0x01);
        //get Z
        header.Z = (byte) ((byte)(header.allBytes[3] >> 6) & 0x01);
        //get AD
        header.AD = (byte) ((byte)(header.allBytes[3] >> 5) & 0x01);
        //get CD
        header.CD = (byte) ((byte)(header.allBytes[3] >> 4) & 0x01);
        //get RCODE
        header.RCODE = (byte) (header.allBytes[3] & 0x0F);
        //get QDCOUNT
        header.QDCOUNT = header.twoBytesToShort(header.allBytes[4], header.allBytes[5]);
        //get ANCOUNT
        header.ANCOUNT = header.twoBytesToShort(header.allBytes[6], header.allBytes[7]);
        //get NSCOUNT
        header.NSCOUNT = header.twoBytesToShort(header.allBytes[8], header.allBytes[9]);
        //get RCOUNT
        header.ARCOUNT = header.twoBytesToShort(header.allBytes[10], header.allBytes[11]);
        return header;
    }

    //this method takes in the DNSMessage request and builds the header response based on that and then returns the header
    static DNSHeader buildHeaderForResponse(DNSMessage request){
        DNSHeader responseHeader = new DNSHeader();
        responseHeader.allBytes = new byte[12];
        responseHeader = request.getHeader();
        //get id bytes
        responseHeader.id = request.getHeader().id;
        //byte 3
        responseHeader.qr = 0x01;
        //byte 4
        responseHeader.RA = 0x01;
        responseHeader.Z = 0x00;
        //RRs
        responseHeader.ANCOUNT = 0x01;
        buildByteArray(responseHeader);
        return responseHeader;
    }

    //this methos builds the byte array for all bytes for the header
    private static void buildByteArray(DNSHeader header) {
        header.allBytes[0] = (byte) ((header.id  >> 8) & 0xFF);
        header.allBytes[1] = (byte) (header.id & 0xFF);
        header.allBytes[2] = (byte) ( ((header.qr << 7) & 0x80) | ((header.opCode << 6) & 0x78) | ((header.AA << 2) & 0x04) | (( header.TC << 1) & 0x02) | ( header.RD & 0x01));
        header.allBytes[3] = (byte)(( (header.RA << 7) & 0x80) | ((byte) (header.Z << 6) & 0x70) | ((header.RCODE & 0x0F)));
        header.allBytes[4] = (byte) (((header.QDCOUNT) >> 8) & 0xFF);
        header.allBytes[5] = (byte) (header.QDCOUNT & 0xFF) ;
        header.allBytes[6] = (byte) ((header.ANCOUNT >> 8) & 0xFF);
        header.allBytes[7] = (byte) (header.ANCOUNT & 0xFF);
        header.allBytes[8] = (byte) ((header.NSCOUNT >> 8) & 0xFF);
        header.allBytes[9] = (byte) (header.NSCOUNT & 0xFF);
        header.allBytes[10] = (byte) ((header.ARCOUNT >> 8) & 0xFF);
        header.allBytes[11] = (byte) (header.ARCOUNT & 0xFF);

    }
    //helper method to convert 2 bytes to a short
    short twoBytesToShort(byte firstByte, byte secondByte){
        return (short) ((short) ((firstByte& 0xFF) << 8) | secondByte & 0xFF);
    }

    int getNumQuestions(){
        return QDCOUNT;
    }
    //ANCOUNT is the number of resource records in the answer section
    int getNumAnswerRRs(){
        return ANCOUNT;
    }
    //NSCOUNT is the number of name server resource records in the authority records section.
    int getNumNameServerRRs(){
        return NSCOUNT;
    }
    //the number of resource records in the additional records section.
    int getNumAdditionalRRs(){
        return ARCOUNT;
    }

    byte[] getAllBytes(){
        return allBytes;
    }


}
