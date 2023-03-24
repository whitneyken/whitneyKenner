import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Base64;

public class Server {
    //Start off with full tcp handshake (3 messages) (syn, syn ack, ack)
    static BigInteger serverPrivateKeyDH;
    static PublicKey CAserverPublicKey;
    static Certificate CAcert;
    static PublicKey serverCertPublicKey;
    static BigInteger SDHPublicKey;
    static Certificate signedCert;
    static BigInteger signedSDHPublicKeyBigInt;

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
        CAcert = Host.getCertificate("../CACertificate.pem");
        CAserverPublicKey = CAcert.getPublicKey();
        //server public key

        signedCert = Host.getCertificate("../CASignedServerCertificate.pem");
        serverCertPublicKey = signedCert.getPublicKey();
        //DH precursor stuff
        SDHPublicKey = Host.getDHPublicKey(serverPrivateKeyDH);

        signedSDHPublicKeyBigInt = Host.getSignedDHPublicKey("../ServerPrivateKey.der", SDHPublicKey, serverCertPublicKey);
        System.out.println("The signed DHpublic key is: " + signedSDHPublicKeyBigInt);

        //System.out.println("verifying sigs for server: " + serverPrivateKeySignature.verify(signaturePubKey));
        ServerSocket servSocket = new ServerSocket(8080);
        Socket socket = servSocket.accept();
        System.out.println("socket has been accepted");
//        ArrayList<byte[]> allMessages = new ArrayList<>();
        ByteArrayOutputStream allMessages = new ByteArrayOutputStream();
        ObjectInputStream serverIn = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream serverOut = new ObjectOutputStream(socket.getOutputStream());
        //receive initial handshake message from client -> initial nonce
        BigInteger nonce = (BigInteger) serverIn.readObject();
        allMessages.write(nonce.toByteArray());
        System.out.println("the nonce I am receiving on the server end is: " + Arrays.toString(nonce.toByteArray()));

        //Server: Server Certificate, DiffieHellman public key, Signed DiffieHellman public key (Sign[g^ks % N, Spriv])
        serverOut.writeObject(signedCert);
        serverOut.writeObject(SDHPublicKey);
        serverOut.writeObject(signedSDHPublicKeyBigInt);
        System.out.println("cert from server: " + signedCert.toString());
        System.out.println("SDHpublic key from server: " + SDHPublicKey.toString());
        System.out.println("signed public key: " + signedSDHPublicKeyBigInt.toString());
        allMessages.write(signedCert.toString().getBytes());
        allMessages.write(SDHPublicKey.toByteArray());
        allMessages.write(signedSDHPublicKeyBigInt.toByteArray());


        //read in client certificate, DH public key and signed key
        Certificate clientCert = (Certificate) serverIn.readObject();
        System.out.println("client cert is: " + clientCert.toString());
        BigInteger clientPublicKeyDH = (BigInteger) serverIn.readObject();
        System.out.println("client DH public key is: " + clientPublicKeyDH);
        BigInteger signedClientPublicKeyDH = (BigInteger) serverIn.readObject();
        System.out.println("signed client DH public key is" + signedClientPublicKeyDH);
        //verify certificate
        clientCert.verify(CAserverPublicKey);
        allMessages.write(clientCert.toString().getBytes());
        allMessages.write(clientPublicKeyDH.toByteArray());
        allMessages.write(signedClientPublicKeyDH.toByteArray());
        //generate shared DH secret & symmetric keys
        makeSharedSecret(clientPublicKeyDH);
        System.out.println("server shared secret is: " + sharedSecret.toString());
        makeSecretKeys(nonce, sharedSecret);

        //send summary
        byte[] toSend = macMessage(allMessages.toByteArray(), serverMAC);
        serverOut.writeObject(toSend);
        allMessages.write(toSend);
        System.out.println("sending handshake MAC to client: " + Arrays.toString(toSend));
        System.out.println("the serverMAC on server end is: " + Arrays.toString(serverMAC));
        //

        byte[] handshakeMACclient = (byte[]) serverIn.readObject();
        System.out.println("from the server the client received: " + Arrays.toString(handshakeMACclient));

        //compare macs

        compareMACS(handshakeMACclient, allMessages.toByteArray());

        //if mac stuff is valid, send response
        allMessages.write(handshakeMACclient);


        //encryption shit
        String message1FromServer = "hello from the other siiiiiiiiiiiiiiide";

        System.out.println("The encrypted message is: " + message1FromServer);

        byte[] encryptedMessage = encrypt(message1FromServer.getBytes(), serverEncrypt, serverIV);

        System.out.println("The cipher message is: " + new String(encryptedMessage));
        serverOut.writeObject(encryptedMessage);

        //receive ack
        byte[] ackFromClient = (byte[]) serverIn.readObject();
        String ack = decrypt(ackFromClient, clientEncrypt, clientIV);
        System.out.println("did we get an ack? " + ack);
        if (!ack.equals("ACK")){
            throw new RuntimeException("ack not received");
        }
        String message2FromServer = "I must have died a thousand tiiiiiiiiimes";
        byte[] encryptedMessage2 = encrypt(message2FromServer.getBytes(), serverEncrypt, serverIV);
        serverOut.writeObject(encryptedMessage2);

        byte[] ackFromClient2 = (byte[]) serverIn.readObject();
        String ack2 = decrypt(ackFromClient2, clientEncrypt, clientIV);
        System.out.println("did we get an ack? " + ack2);
        if (!ack2.equals("ACK")){
            throw new RuntimeException("ack not received");
        }

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
        byte[] macBoiBytes = macMessage(noMAC, clientMAC);
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
        byte[] messageMACedforEnd = macMessage(message, serverMAC);
        encryptedMessage.write(messageMACedforEnd);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(encrypt, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(encryptedMessage.toByteArray());
    }

    private static void compareMACS(byte[] fromServer, byte[] fromMe) throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        byte[] myBytes = macMessage(fromMe, clientMAC);
        System.out.println("my bytes: " + Arrays.toString(myBytes));
        System.out.println("server bytes: " + Arrays.toString(fromServer));
        for (int i = 0; i < fromServer.length; i++){
            if (fromServer[i] != myBytes[i]){
                throw new RuntimeException("message hash mismatch");
            }
        }
    }
    public static byte[] macMessage(byte[] messages, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        Mac HMAC = Mac.getInstance("HmacSHA256");
        ByteArrayOutputStream toSend = new ByteArrayOutputStream();

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
        HMAC.init(secretKeySpec);
        HMAC.update(messages);

        return HMAC.doFinal();
    }
    public static void makeSharedSecret(BigInteger DHPublicKey) {
        sharedSecret = Host.getSharedSecret(DHPublicKey, serverPrivateKeyDH);
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
