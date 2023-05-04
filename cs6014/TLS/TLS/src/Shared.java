import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

public class Shared {
    static private final String nString = "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AACAA68FFFFFFFFFFFFFFFF";
    static public BigInteger nBigInt = new BigInteger(nString, 16);
    static public BigInteger gBigInt = new BigInteger("2");


    static public Certificate getCertificate(String filepath) {
        try {
            //get public key from passed in file path
            FileInputStream fileInput = new FileInputStream(filepath);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            //certificate
            return certificateFactory.generateCertificate(fileInput);

        }catch (FileNotFoundException | CertificateException e){
            throw new RuntimeException(e);
        }

    }

    static public BigInteger getSignedDHPublicKey(String filepath, BigInteger publicKeyDH, PublicKey publicKeyCA){
        try {
            //get private key from passed in file path
            FileInputStream fileInput = new FileInputStream(filepath);
            byte[] privateKeyDerr = fileInput.readAllBytes();
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyDerr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            //generating private key
            PrivateKey privKey =  keyFactory.generatePrivate(spec);

            //signing and updating with server private key
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initSign(privKey);
            sig.update(publicKeyDH.toByteArray());
            byte[] signaturePubKey = sig.sign();

            //verification and update
            sig.initVerify(publicKeyCA);
            sig.update(publicKeyDH.toByteArray());
            System.out.println("verifying sigs for: " + sig.verify(signaturePubKey));
            return new BigInteger(signaturePubKey);

        }catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException |
                SignatureException e){
            throw new RuntimeException(e);
        }
    }

    public static BigInteger getDHPublicKey(BigInteger privKeyDH) {
         return gBigInt.modPow(privKeyDH, nBigInt);
    }

    public static BigInteger getSharedSecret(BigInteger dhPublicKey, BigInteger privateKeyDH) {
        return dhPublicKey.modPow(privateKeyDH, nBigInt);
    }

    public static byte[] macMessage(byte[] messages, byte[] MAC) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        Mac HMAC = Mac.getInstance("HmacSHA256");

        SecretKeySpec secretKeySpec = new SecretKeySpec(MAC, "HmacSHA256");
        HMAC.init(secretKeySpec);
        HMAC.update(messages);

        return HMAC.doFinal();

    }
    public static byte[] encrypt(byte[] message, byte[] encrypt, byte[] iv, byte[] mac) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        ByteArrayOutputStream encryptedMessage = new ByteArrayOutputStream();
        encryptedMessage.write(message);
        byte[] messageMACedforEnd = Shared.macMessage(message, mac);
        encryptedMessage.write(messageMACedforEnd);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(encrypt, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(encryptedMessage.toByteArray());
    }
    public static void compareMACS(byte[] fromOtherSide, byte[] fromMe, byte[] mac) throws NoSuchAlgorithmException, IOException, InvalidKeyException {
        byte[] myHashedBytes = Shared.macMessage(fromMe, mac);
        if (!Arrays.equals(myHashedBytes, fromOtherSide)){
            throw new RuntimeException("message hash mismatch");
        }

    }

    public static String decrypt(byte[] cipherText, byte[] encrypt, byte[] iv, byte[] mac) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(encrypt, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] plainText = cipher.doFinal(cipherText);

        //separate things and check mac

        byte[] noMAC = Arrays.copyOf(plainText, plainText.length - 32);
        byte[] yesMAC = Arrays.copyOfRange(plainText, plainText.length - 32, plainText.length);
        byte[] macBoiBytes = Shared.macMessage(noMAC, mac);
        if (Arrays.equals(macBoiBytes, yesMAC)) {
            return new String(noMAC);
        } else {
            throw new RuntimeException("MAC does not match");
        }

    }
}
