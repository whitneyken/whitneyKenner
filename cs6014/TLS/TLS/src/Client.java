import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
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
    static BigInteger CDHPublicKey;
    static BigInteger clientNonce;
    static Certificate CAcert;
    static PublicKey CAclientPublicKey;
    static Certificate signedCert;
    static PublicKey clientCertPublicKey;
    static BigInteger signedCDHPublicKey;
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
        clientNonce = new BigInteger(new SecureRandom().generateSeed(32));
        //System.out.println(Arrays.toString(serverRandom));
        //CA public key
        CAcert = Host.getCertificate("../CACertificate.pem");
        CAclientPublicKey = CAcert.getPublicKey();
        //generate certificate with CA signed server cert . pem
        signedCert = Host.getCertificate("../CASignedClientCertificate.pem");
        clientCertPublicKey = signedCert.getPublicKey();


        CDHPublicKey = Host.getDHPublicKey(clientPrivateKeyDH);

        signedCDHPublicKey = Host.getSignedDHPublicKey("../ClientPrivateKey.der", CDHPublicKey, clientCertPublicKey);
        //= new BigInteger(signaturePubKeyClient);
        System.out.println("The signed DHpublic key is: " + signedCDHPublicKey);
        ByteArrayOutputStream allMessages = new ByteArrayOutputStream();
        //client verification
        Socket clientSocket = new Socket("localhost", 8080);
        System.out.println("socket is accepted on client side?");
        ObjectOutputStream clientOut = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream clientIn = new ObjectInputStream(clientSocket.getInputStream());

        //sending message 1 of the handshake -> client nonce
        System.out.println("the nonce I am sending from the client is: " + Arrays.toString(clientNonce.toByteArray()));

        clientOut.writeObject(clientNonce);
        allMessages.write(clientNonce.toByteArray());

        //read in certificate, DHpublic and signed DHpublic from server
        Certificate serverCert = (Certificate) clientIn.readObject();
        System.out.println("server cert is: " + serverCert.toString());
        BigInteger serverPublicKeyDH = (BigInteger) clientIn.readObject();
        System.out.println("server DH public key is: " + serverPublicKeyDH);
        BigInteger signedServerPublicKeyDH = (BigInteger) clientIn.readObject();
        System.out.println("signed server DH public key is" + signedServerPublicKeyDH);
        //verify certificate
        serverCert.verify(CAclientPublicKey);
        allMessages.write(serverCert.toString().getBytes());
        allMessages.write(serverPublicKeyDH.toByteArray());
        allMessages.write(signedServerPublicKeyDH.toByteArray());
        //generate shared DH secret & symmetric keys
         makeSharedSecret(serverPublicKeyDH);
        System.out.println("client shared secret is: " + sharedSecret.toString());
        makeSecretKeys(clientNonce, sharedSecret);

        //send response message with cert, DHPublic, signedDHPublic
        clientOut.writeObject(signedCert);
        clientOut.writeObject(CDHPublicKey);
        clientOut.writeObject(signedCDHPublicKey);
        System.out.println("the sent certificate from the client is: " + signedCert.toString());
        System.out.println("the client public key is: " + CDHPublicKey.toString());
        System.out.println("the signed public key is: " + signedCDHPublicKey.toString());
        allMessages.write(signedCert.toString().getBytes());
        allMessages.write(CDHPublicKey.toByteArray());
        allMessages.write(signedCDHPublicKey.toByteArray());

        //receive summary
        byte[] handshakeMACserver = (byte[]) clientIn.readObject();
        System.out.println("from the server the client received: " + Arrays.toString(handshakeMACserver));

        //compare macs
        System.out.println("the serverMAC on clien tend is: " + Arrays.toString(serverMAC));

        compareMACS(handshakeMACserver, allMessages.toByteArray());

        //if mac stuff is valid, send response
        allMessages.write(handshakeMACserver);
        byte[] toSend = macMessage(allMessages.toByteArray(), clientMAC);
        clientOut.writeObject(toSend);
        allMessages.write(toSend);


        //messaging begins, see if I can receive message from server:
        byte[] encryptedMessage = (byte[]) clientIn.readObject();
        System.out.println("the encrypted message is: " + Arrays.toString(encryptedMessage));
        String decryptedMessage = decrypt(encryptedMessage, serverEncrypt, serverIV);
        System.out.println("The decrypted message is: " + decryptedMessage);

        //send ACK
        byte[] encryptedAck = encrypt(ack.getBytes(), clientEncrypt, clientIV);
        clientOut.writeObject(encryptedAck);

        //receive message 2
        byte[] encryptedMessage2 = (byte[]) clientIn.readObject();
        String decryptedMessage2 = decrypt(encryptedMessage2, serverEncrypt, serverIV);
        System.out.println("the decrypted message is: " + decryptedMessage2);

        //ack2
        byte[] encryptedAck2 = encrypt(ack.getBytes(), clientEncrypt, clientIV);
        clientOut.writeObject(encryptedAck2);
    }

    public static String decrypt(byte[] cipherText, byte[] encrypt, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(encrypt, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] plainText = cipher.doFinal(cipherText);

        //separate things and check mac

        byte[] noMAC = Arrays.copyOf(plainText, plainText.length - 32);
        byte[] yesMAC = Arrays.copyOfRange(plainText, plainText.length - 32, plainText.length);
        System.out.println("the non concatenated thing is: " + new String(noMAC));
        byte[] macBoiBytes = macMessage(noMAC, serverMAC);
        System.out.println("from server MAC: " + new String(yesMAC));
        System.out.println("from my MAC: " + new String(macBoiBytes));
        if (Arrays.equals(macBoiBytes, yesMAC)) {
            return new String(noMAC);
        } else {
            throw new RuntimeException("MAC does not match");
        }
    }
    public static byte[] encrypt(byte[] message, byte[] encrypt, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        ByteArrayOutputStream encryptedMessage = new ByteArrayOutputStream();
        encryptedMessage.write(message);
        byte[] messageMACedforEnd = macMessage(message, clientMAC);
        encryptedMessage.write(messageMACedforEnd);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(encrypt, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(encryptedMessage.toByteArray());
    }

    private static void compareMACS(byte[] fromServer, byte[] fromMe) throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        byte[] myHashedBytes = macMessage(fromMe, serverMAC);
        System.out.println("my bytes: " + Arrays.toString(myHashedBytes));
        System.out.println("server bytes: " + Arrays.toString(fromServer));
        for (int i = 0; i < fromServer.length; i++) {
            if (fromServer[i] != myHashedBytes[i]) {
                throw new RuntimeException("message hash mismatch");
            }
        }

    }

    public static byte[] macMessage(byte[] messages, byte[] MAC) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        Mac HMAC = Mac.getInstance("HmacSHA256");

        SecretKeySpec secretKeySpec = new SecretKeySpec(MAC, "HmacSHA256");
        HMAC.init(secretKeySpec);
        HMAC.update(messages);

        return HMAC.doFinal();

    }
    public static void makeSharedSecret(BigInteger DHPublicKey) {
        sharedSecret = Host.getSharedSecret(DHPublicKey, clientPrivateKeyDH);
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

