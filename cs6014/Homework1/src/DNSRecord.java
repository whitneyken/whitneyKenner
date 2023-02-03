import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DNSRecord {
    long dateRecorded;
    ArrayList<String> domainName;
    short type, classCode, rdLength;
    int TTL;
    byte[] rData;

    //this method takes in a byte array output stream and the DNSmessage containing the records to decode and returns a DNSRecord
    static DNSRecord decodeRecord(ByteArrayInputStream inputStream, DNSMessage message){
        inputStream.mark(Integer.MAX_VALUE);
        DNSRecord record = new DNSRecord();
        record.dateRecorded = Instant.now().getEpochSecond();
        byte byteToCheckIfCompressed = (byte) inputStream.read();
//        if there is compression
        if (((byteToCheckIfCompressed >> 6) & 0x03) == 0x03){
            byte secondOffsetByte = (byte) inputStream.read();
            int offSet = ((0x3F & byteToCheckIfCompressed) << 8) | secondOffsetByte;
            record.domainName = message.readDomainName(offSet);
        }else {
            inputStream.reset();
            record.domainName = message.readDomainName(inputStream);
        }
        byte firstTypeByte = (byte) inputStream.read();
        byte secondTypeByte = (byte) inputStream.read();
        record.type = record.twoBytesToShort(firstTypeByte, secondTypeByte);
        byte firstClassByte = (byte) inputStream.read();
        byte secondClassByte = (byte) inputStream.read();
        record.classCode = record.twoBytesToShort(firstClassByte, secondClassByte);
        byte ttlByte1 = (byte) inputStream.read();
        byte ttlByte2 = (byte) inputStream.read();
        byte ttlByte3 = (byte) inputStream.read();
        byte ttlByte4 = (byte) inputStream.read();
        record.TTL = record.fourBytesToInt(ttlByte1, ttlByte2, ttlByte3, ttlByte4);
        byte rdLengthByte1 = (byte) inputStream.read();
        byte rdLengthByte2 = (byte) inputStream.read();
        record.rdLength = record.twoBytesToShort(rdLengthByte1, rdLengthByte2);
        record.rData = new byte[record.rdLength];
        for (int i = 0; i < record.rdLength; i++){
            byte tempByte = (byte) inputStream.read();
            record.rData[i] = (byte) (tempByte & 0xFF);
        }
        return record;

    }

    //this method takes in a byte array output stream and hashmap of the domain locations. nothing is returned
    void writeBytes(ByteArrayOutputStream outputStream, HashMap<String, Integer> domainLocations) throws IOException {
        DNSMessage.writeDomainName(outputStream, domainLocations, this.domainName);
        short typeToSend = this.type;
        outputStream.write((byte) ((typeToSend >> 8) & 0xFF));
        outputStream.write((byte) (typeToSend & 0xFF));
        short classCodeToSend = this.classCode;
        outputStream.write((byte) ((classCodeToSend >> 8 ) & 0xFF));
        outputStream.write((byte) (classCodeToSend & 0xFF));
        int TTLToSend = this.TTL;
        outputStream.write((byte)(TTLToSend >> 24) & 0xFF);
        outputStream.write((byte)(TTLToSend >> 16) & 0xFF);
        outputStream.write((byte)(TTLToSend >> 8) & 0xFF);
        outputStream.write((byte)(TTLToSend & 0xFF));
        short RDLengthToSend = this.rdLength;
        outputStream.write((byte)(RDLengthToSend >> 8) & 0xFF);
        outputStream.write((byte)(RDLengthToSend & 0xFF));
        if (RDLengthToSend > 0) {
            outputStream.write(this.rData);
        }
    }


    boolean isExpired() {
        return (dateRecorded + TTL ) < Instant.now().getEpochSecond();
    }
    short twoBytesToShort(byte firstByte, byte secondByte) {
        return (short) ((short) ((firstByte & 0xFF) << 8) | secondByte & 0xFF);
    }
    int fourBytesToInt(byte firstByte, byte secondByte, byte thirdByte, byte fourthByte){
        return (((firstByte & 0xFF) << 24) |  ((secondByte & 0xFF) << 16) | ((thirdByte & 0xFF) << 8)) | (fourthByte & 0xFF);
    }

    @Override
    public String toString() {
        return "DNSRecord{" +
                "dateRecorded=" + dateRecorded +
                ", domainName=" + domainName +
                ", type=" + type +
                ", classCode=" + classCode +
                ", TTL=" + TTL +
                ", rdLength=" + rdLength +
                ", rData=" + Arrays.toString(rData) +
                '}';
    }
}


