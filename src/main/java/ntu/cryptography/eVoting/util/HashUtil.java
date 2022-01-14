package ntu.cryptography.eVoting.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;

public class HashUtil {

    private static String getSHA256(String input){

        String hashValue = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            hashValue = String.format("%064x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hashValue;
    }

    private static String getSHA512(String input){

        String hashValue = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            hashValue = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hashValue;
    }


    private static String getTimeStamp(){

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        String ts = String.valueOf(stamp.getTime());	//long to String

        return ts;

    }


    public static String getTokenSHA256(String studentId){
        String timeStamp = getTimeStamp();
        String inputValue = studentId + timeStamp;

        return getSHA256(inputValue);
    }

    public static String getTokenSHA512(String studentId){
        String timeStamp = getTimeStamp();
        String inputValue = studentId + timeStamp;

        return getSHA512(inputValue);
    }

}