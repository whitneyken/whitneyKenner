import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;

public class Server {
    //Start off with full tcp handshake (3 messages) (syn, syn ack, ack)
    static BigInteger serverPrivateKeyDH;
    static BigInteger sharedSecret;
    static byte[] serverEncrypt;
    static byte[] clientEncrypt;
    static byte[] serverMAC;
    static byte[] clientMAC;
    static byte[] serverIV;
    static byte[] clientIV;


    //make constructor!!!!!
    public static void main(String[] args) throws IOException, ClassNotFoundException, CertificateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        //generating our random private key
        serverPrivateKeyDH = new BigInteger(new SecureRandom().generateSeed(32));
        //files for certificates

        //CA public key
        Certificate CAcert = Shared.getCertificate("../CACertificate.pem");
        PublicKey CAserverPublicKey = CAcert.getPublicKey();
        //server public key

        Certificate signedCert = Shared.getCertificate("../CASignedServerCertificate.pem");
        PublicKey serverCertPublicKey = signedCert.getPublicKey();
        //DH precursor stuff
        BigInteger SDHPublicKey = Shared.getDHPublicKey(serverPrivateKeyDH);

        BigInteger signedSDHPublicKeyBigInt = Shared.getSignedDHPublicKey("../ServerPrivateKey.der", SDHPublicKey, serverCertPublicKey);

        //System.out.println("verifying sigs for server: " + serverPrivateKeySignature.verify(signaturePubKey));
        ServerSocket servSocket = new ServerSocket(8080);
        Socket socket = servSocket.accept();
//        ArrayList<byte[]> allMessages = new ArrayList<>();
        ByteArrayOutputStream allMessages = new ByteArrayOutputStream();
        ObjectInputStream serverIn = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream serverOut = new ObjectOutputStream(socket.getOutputStream());
        //receive initial handshake message from client -> initial nonce
        BigInteger nonce = (BigInteger) serverIn.readObject();
        allMessages.write(nonce.toByteArray());

        //Server: Server Certificate, DiffieHellman public key, Signed DiffieHellman public key (Sign[g^ks % N, Spriv])
        serverOut.writeObject(signedCert);
        serverOut.writeObject(SDHPublicKey);
        serverOut.writeObject(signedSDHPublicKeyBigInt);
        allMessages.write(signedCert.toString().getBytes());
        allMessages.write(SDHPublicKey.toByteArray());
        allMessages.write(signedSDHPublicKeyBigInt.toByteArray());


        //read in client certificate, DH public key and signed key
        Certificate clientCert = (Certificate) serverIn.readObject();
        BigInteger clientPublicKeyDH = (BigInteger) serverIn.readObject();
        BigInteger signedClientPublicKeyDH = (BigInteger) serverIn.readObject();
        //verify certificate
        clientCert.verify(CAserverPublicKey);
        allMessages.write(clientCert.toString().getBytes());
        allMessages.write(clientPublicKeyDH.toByteArray());
        allMessages.write(signedClientPublicKeyDH.toByteArray());
        //generate shared DH secret & symmetric keys
        makeSharedSecret(clientPublicKeyDH);
        makeSecretKeys(nonce, sharedSecret);

        //send summary
        byte[] toSend = Shared.macMessage(allMessages.toByteArray(), serverMAC);
        serverOut.writeObject(toSend);
        allMessages.write(toSend);
        //
        byte[] handshakeMACclient = (byte[]) serverIn.readObject();

        //compare macs

        Shared.compareMACS(handshakeMACclient, allMessages.toByteArray(), clientMAC);

        //if mac stuff is valid, send response
        allMessages.write(handshakeMACclient);


        //encryption shit
        String message1FromServer = "hello from the other siiiiiiiiiiiiiiide";

        System.out.println("The encrypted message is: " + message1FromServer);

        byte[] encryptedMessage = Shared.encrypt(message1FromServer.getBytes(), serverEncrypt, serverIV, serverMAC);

        System.out.println("The cipher message is: " + new String(encryptedMessage));
        serverOut.writeObject(encryptedMessage);

        //receive ack
        byte[] ackFromClient = (byte[]) serverIn.readObject();
        String ack = Shared.decrypt(ackFromClient, clientEncrypt, clientIV, clientMAC);
        System.out.println("did we get an ack? " + ack);
        if (!ack.equals("ACK")){
            throw new RuntimeException("ack not received");
        }
        String message2FromServer = "I must have died a thousand tiiiiiiiiimes";
        byte[] encryptedMessage2 = Shared.encrypt(message2FromServer.getBytes(), serverEncrypt, serverIV, serverMAC);
        serverOut.writeObject(encryptedMessage2);

        byte[] ackFromClient2 = (byte[]) serverIn.readObject();
        String ack2 = Shared.decrypt(ackFromClient2, clientEncrypt, clientIV, clientMAC);
        System.out.println("did we get an ack? " + ack2);
        if (!ack2.equals("ACK")){
            throw new RuntimeException("ack not received");
        }
    }

    public static void makeSharedSecret(BigInteger DHPublicKey) {
        sharedSecret = Shared.getSharedSecret(DHPublicKey, serverPrivateKeyDH);
    }

    private static byte[] hdkfExpand(byte[] input, String tag) {
        //tag is a string, but probably convenient to take its contents as byte[]
        try {
            Mac HMAC = Mac.getInstance("HmacSHA256");

            SecretKeySpec secretKeySpec = new SecretKeySpec(input, "HmacSHA256");
            HMAC.init(secretKeySpec);
            HMAC.update(tag.getBytes());
            HMAC.update((byte) 0x01);
            return Arrays.copyOf(HMAC.doFinal(), 16);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeSecretKeys(BigInteger clientNonce, BigInteger sharedSecretFromDiffieHellman) {
        try {
            Mac HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clientNonce.toByteArray(), "HmacSHA256");
            HMAC.init(secretKeySpec);
            HMAC.update(sharedSecretFromDiffieHellman.toByteArray());
            byte[] prk = HMAC.doFinal();

            serverEncrypt = hdkfExpand(prk, "server encrypt");
            clientEncrypt = hdkfExpand(serverEncrypt, "client encrypt");
            serverMAC = hdkfExpand(clientEncrypt, "server MAC");
            clientMAC = hdkfExpand(serverMAC, "client MAC");
            serverIV = hdkfExpand(clientMAC, "server IV");
            clientIV = hdkfExpand(serverIV, "client IV");
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
