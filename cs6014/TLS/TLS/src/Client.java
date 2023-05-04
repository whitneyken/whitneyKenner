import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;

public class Client {
    static String ack = "ACK";
    static BigInteger clientPrivateKeyDH;
    static BigInteger sharedSecret;
    static byte[] serverEncrypt;
    static byte[] clientEncrypt;
    static byte[] serverMAC;
    static byte[] clientMAC;
    static byte[] serverIV;
    static byte[] clientIV;

    public static void main(String[] args) throws IOException, ClassNotFoundException, CertificateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, NoSuchProviderException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        //client
        clientPrivateKeyDH = new BigInteger(new SecureRandom().generateSeed(32));
        //client nonce
        BigInteger clientNonce = new BigInteger(new SecureRandom().generateSeed(32));
        //System.out.println(Arrays.toString(serverRandom));
        //CA public key
        Certificate CAcert = Shared.getCertificate("../CACertificate.pem");
        PublicKey CAPublicKey = CAcert.getPublicKey();
        //generate certificate with CA signed server cert . pem
        Certificate signedCert = Shared.getCertificate("../CASignedClientCertificate.pem");
        PublicKey clientCertPublicKey = signedCert.getPublicKey();


        BigInteger CDHPublicKey = Shared.getDHPublicKey(clientPrivateKeyDH);

        BigInteger signedCDHPublicKey = Shared.getSignedDHPublicKey("../ClientPrivateKey.der", CDHPublicKey, clientCertPublicKey);
        //= new BigInteger(signaturePubKeyClient);
        ByteArrayOutputStream allMessages = new ByteArrayOutputStream();
        //client verification
        Socket clientSocket = new Socket("localhost", 8080);;
        ObjectOutputStream clientOut = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream clientIn = new ObjectInputStream(clientSocket.getInputStream());

        //sending message 1 of the handshake -> client nonce
        clientOut.writeObject(clientNonce);
        allMessages.write(clientNonce.toByteArray());

        //read in certificate, DHpublic and signed DHpublic from server
        Certificate serverCert = (Certificate) clientIn.readObject();
        BigInteger serverPublicKeyDH = (BigInteger) clientIn.readObject();
        BigInteger signedServerPublicKeyDH = (BigInteger) clientIn.readObject();
        //verify certificate
        serverCert.verify(CAPublicKey);
        allMessages.write(serverCert.toString().getBytes());
        allMessages.write(serverPublicKeyDH.toByteArray());
        allMessages.write(signedServerPublicKeyDH.toByteArray());
        //generate shared DH secret & symmetric keys
         makeSharedSecret(serverPublicKeyDH);
        makeSecretKeys(clientNonce, sharedSecret);

        //send response message with cert, DHPublic, signedDHPublic
        clientOut.writeObject(signedCert);
        clientOut.writeObject(CDHPublicKey);
        clientOut.writeObject(signedCDHPublicKey);
        allMessages.write(signedCert.toString().getBytes());
        allMessages.write(CDHPublicKey.toByteArray());
        allMessages.write(signedCDHPublicKey.toByteArray());

        //receive summary
        byte[] handshakeMACserver = (byte[]) clientIn.readObject();
        //compare macs
        Shared.compareMACS(handshakeMACserver, allMessages.toByteArray(), serverMAC);
        //if mac stuff is valid, send response
        allMessages.write(handshakeMACserver);
        byte[] toSend = Shared.macMessage(allMessages.toByteArray(), clientMAC);
        clientOut.writeObject(toSend);
        allMessages.write(toSend);
        //messaging begins, see if I can receive message from server:
        byte[] encryptedMessage = (byte[]) clientIn.readObject();
        System.out.println("the encrypted message is: " + Arrays.toString(encryptedMessage));
        String decryptedMessage = Shared.decrypt(encryptedMessage, serverEncrypt, serverIV, serverMAC);
        System.out.println("The decrypted message is: " + decryptedMessage);
        //send ACK
        byte[] encryptedAck = Shared.encrypt(ack.getBytes(), clientEncrypt, clientIV, clientMAC);
        clientOut.writeObject(encryptedAck);

        //receive message 2
        byte[] encryptedMessage2 = (byte[]) clientIn.readObject();
        System.out.println("the encrypted message is: " + Arrays.toString(encryptedMessage2));
        String decryptedMessage2 = Shared.decrypt(encryptedMessage2, serverEncrypt, serverIV, serverMAC);
        System.out.println("the decrypted message is: " + decryptedMessage2);
        //ack2
        byte[] encryptedAck2 = Shared.encrypt(ack.getBytes(), clientEncrypt, clientIV, clientMAC);
        clientOut.writeObject(encryptedAck2);
    }

    public static void makeSharedSecret(BigInteger DHPublicKey) {
        sharedSecret = Shared.getSharedSecret(DHPublicKey, clientPrivateKeyDH);
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

