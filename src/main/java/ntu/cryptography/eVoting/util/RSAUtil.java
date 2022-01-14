package ntu.cryptography.eVoting.util;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;


public class RSAUtil {

    public static List<String> keyGen() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        String generatedPrivateKey = Base64.encodeBase64String(privateKey.getEncoded());
        String generatedPublicKey = Base64.encodeBase64String(publicKey.getEncoded());
        return Arrays.asList(generatedPrivateKey, generatedPublicKey);
    }


    public static String decode(Key privatekey, byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privatekey);
        return new String(cipher.doFinal(data));
    }

    public static String decodebase64(Key privatekey, String text) throws Exception {
        return decode(privatekey, java.util.Base64.getDecoder().decode(text));
    }

    public static Key convertKey(String privateKey) throws Exception{
        byte[] privateKeyBytes = java.util.Base64.getDecoder().decode(privateKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateKey2 = keyFactory.generatePrivate(spec);

        return privateKey2;
    }

}
