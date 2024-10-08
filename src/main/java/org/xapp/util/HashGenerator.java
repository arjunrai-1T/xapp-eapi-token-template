package org.xapp.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.security.SecureRandom;

/*
SHA Algorithms
 Secure hash algorithm (SHA) is also one type of Cryptographic function. The algorithm is similar to MD5. The SHA algorithm,
 on the other hand, produces much stronger hashes than the MD5 algorithm. The SHA algorithm generates hashes that aren't always unique,
 which means there's a chance of collision. SHA, on the other hand, has a much lower collision rate than MD5. The output of SHA is called hashcode,
 there are four types of SHA algorithms based upon the size of the hash generated.

    SHA-1 - It is the most basic SHA. It generates a hashcode of 160 bits.

    SHA-256 - It has a higher level of security than SHA-1. It generates a hash with a length of 256.

    SHA-384 - SHA-384 is a one-level higher than SHA-256, with a 384-bit hash.

    SHA-512 - It is the most powerful of all the SHAs mentioned. It generates a 512-bit hash.

    Ref:  https://randomkeygen.com

 */

@Component
public class HashGenerator {

<<<<<<< HEAD
=======
    public static Logger logger = LogManager.getLogger(HashGenerator.class);

>>>>>>> 1e30f501ab0857189871ea203db67a439334df0f
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";

    public static void TestGenerateHashFromAnyString(String word) {
        //Ref:  https://randomkeygen.com
<<<<<<< HEAD
        System.out.println("TestGenerateHashFromAnyString SHA-1:   " + generateHash(word,"SHA-1") );
        System.out.println("TestGenerateHashFromAnyString SHA-256: " + generateHash(word,"SHA-256") );
        System.out.println("TestGenerateHashFromAnyString SHA-384: " + generateHash(word,"SHA-384") );
        System.out.println("TestGenerateHashFromAnyString SHA-512: " + generateHash(word,"SHA-512") );
=======
        logger.info("TestGenerateHashFromAnyString SHA-1:   " + generateHash(word,"SHA-1") );
        logger.info("TestGenerateHashFromAnyString SHA-256: " + generateHash(word,"SHA-256") );
        logger.info("TestGenerateHashFromAnyString SHA-384: " + generateHash(word,"SHA-384") );
        logger.info("TestGenerateHashFromAnyString SHA-512: " + generateHash(word,"SHA-512") );
>>>>>>> 1e30f501ab0857189871ea203db67a439334df0f
    }

    public static void TestGenerateWEPKeyExample() {
        //Ref:  https://randomkeygen.com
<<<<<<< HEAD
        System.out.println("TestGenerateWEPKeyExample 64-bit WEP Key: " + generateWEPKey(5));  //Example 766D4
        System.out.println("TestGenerateWEPKeyExample 128-bit WEP Key: " + generateWEPKey(13)); //Example E42ABB1C3498C
        System.out.println("TestGenerateWEPKeyExample 152-bit WEP Key: " + generateWEPKey(16)); //Example 96D376B574152283
        System.out.println("TestGenerateWEPKeyExample 160-bit WEP Key: " + generateWEPKey(20)); //Example G+U]oVIC:xW{/>BTJz?O
        System.out.println("TestGenerateWEPKeyExample 256-bit WEP Key: " + generateWEPKey(29)); //Example 979615D89A618CFED28C492ACAACC
        System.out.println("TestGenerateWEPKeyExample 504-bit WEP Key: " + generateWEPKey(63)); //Example L0mtOfp?nfi'?{>`"s|03H"Wza.Y&mV86okw(/Td:(AN<"hT3FN'4YdnnK<lRY)
=======
        logger.info("TestGenerateWEPKeyExample 64-bit WEP Key: " + generateWEPKey(5));  //Example 766D4
        logger.info("TestGenerateWEPKeyExample 128-bit WEP Key: " + generateWEPKey(13)); //Example E42ABB1C3498C
        logger.info("TestGenerateWEPKeyExample 152-bit WEP Key: " + generateWEPKey(16)); //Example 96D376B574152283
        logger.info("TestGenerateWEPKeyExample 160-bit WEP Key: " + generateWEPKey(20)); //Example G+U]oVIC:xW{/>BTJz?O
        logger.info("TestGenerateWEPKeyExample 256-bit WEP Key: " + generateWEPKey(29)); //Example 979615D89A618CFED28C492ACAACC
        logger.info("TestGenerateWEPKeyExample 504-bit WEP Key: " + generateWEPKey(63)); //Example L0mtOfp?nfi'?{>`"s|03H"Wza.Y&mV86okw(/Td:(AN<"hT3FN'4YdnnK<lRY)
>>>>>>> 1e30f501ab0857189871ea203db67a439334df0f
    }

    public static void TestGenerateWEPKeyFromStringExample(String word) {
        //Ref:  https://randomkeygen.com
<<<<<<< HEAD
        System.out.println("TestGenerateWEPKeyFromStringExample 64-bit WEP Key: " + generateWEPKeyFromString (word, 5));  //Example 766D4
        System.out.println("TestGenerateWEPKeyFromStringExample 128-bit WEP Key: " + generateWEPKeyFromString(word, 13)); //Example E42ABB1C3498C
        System.out.println("TestGenerateWEPKeyFromStringExample 152-bit WEP Key: " + generateWEPKeyFromString(word, 16)); //Example 96D376B574152283
        System.out.println("TestGenerateWEPKeyFromStringExample 160-bit WEP Key: " + generateWEPKeyFromString(word,20));  //Example G+U]oVIC:xW{/>BTJz?O
        System.out.println("TestGenerateWEPKeyFromStringExample 256-bit WEP Key: " + generateWEPKeyFromString(word, 29)); //Example 979615D89A618CFED28C492ACAACC
        System.out.println("TestGenerateWEPKeyFromStringExample 504-bit WEP Key: " + generateWEPKeyFromString(word,63));  //Example L0mtOfp?nfi'?{>`"s|03H"Wza.Y&mV86okw(/Td:(AN<"hT3FN'4YdnnK<lRY)
=======
        logger.info("TestGenerateWEPKeyFromStringExample 64-bit WEP Key: " + generateWEPKeyFromString (word, 5));  //Example 766D4
        logger.info("TestGenerateWEPKeyFromStringExample 128-bit WEP Key: " + generateWEPKeyFromString(word, 13)); //Example E42ABB1C3498C
        logger.info("TestGenerateWEPKeyFromStringExample 152-bit WEP Key: " + generateWEPKeyFromString(word, 16)); //Example 96D376B574152283
        logger.info("TestGenerateWEPKeyFromStringExample 160-bit WEP Key: " + generateWEPKeyFromString(word,20));  //Example G+U]oVIC:xW{/>BTJz?O
        logger.info("TestGenerateWEPKeyFromStringExample 256-bit WEP Key: " + generateWEPKeyFromString(word, 29)); //Example 979615D89A618CFED28C492ACAACC
        logger.info("TestGenerateWEPKeyFromStringExample 504-bit WEP Key: " + generateWEPKeyFromString(word,63));  //Example L0mtOfp?nfi'?{>`"s|03H"Wza.Y&mV86okw(/Td:(AN<"hT3FN'4YdnnK<lRY)
>>>>>>> 1e30f501ab0857189871ea203db67a439334df0f
    }

    public static void TestGenerateFortKnoxHashExample(String word) {
        //Ref:  https://randomkeygen.com
        //Fort Knox Passwords  Hash RLPN3@{X)3Z>`^fb*0fVD{xxsNA7Gu ,7~Lw&l}t$3/N1b7t@M}Q{)d'{{%F:f,KTK_bRN*1#Y.MEcN|AW==OG8Wo#U-^,*~@7MIK{yG:L~wSABJ7{e_sldM!^;f
<<<<<<< HEAD
        System.out.println("TestGenerateFortKnoxHashExample Random Hash: " + generateFortKnoxHash(32));
        System.out.println("TestGenerateFortKnoxHashExample Hash from String: " + generateFortKnoxHashFromWord(word, 32));
=======
        logger.info("TestGenerateFortKnoxHashExample Random Hash: " + generateFortKnoxHash(32));
        logger.info("TestGenerateFortKnoxHashExample Hash from String: " + generateFortKnoxHashFromWord(word, 32));
>>>>>>> 1e30f501ab0857189871ea203db67a439334df0f
    }

    public static void TestGenerateWPAKeyExample() {
        //Ref:  https://randomkeygen.com
<<<<<<< HEAD
        System.out.println("TestGenerateWPAKeyExample Generated 160-bit WPA Key: " + generateWPAKey(20));
        System.out.println("TestGenerateWPAKeyExample Generated 504-bit WPA Key: " + generateWPAKey(63));
=======
        logger.info("TestGenerateWPAKeyExample Generated 160-bit WPA Key: " + generateWPAKey(20));
        logger.info("TestGenerateWPAKeyExample Generated 504-bit WPA Key: " + generateWPAKey(63));
>>>>>>> 1e30f501ab0857189871ea203db67a439334df0f
    }

    public static void TestGenerateWPAKeyFromWordExample(String word) {
        //Ref:  https://randomkeygen.com
<<<<<<< HEAD
        System.out.println("TestGenerateWPAKeyFromWordExample Generated 160-bit WPA Key: " + generateWPAKeyFromWord(word, 20));
        System.out.println("TestGenerateWPAKeyFromWordExample Generated 504-bit WPA Key: " + generateWPAKeyFromWord(word, 63));
=======
        logger.info("TestGenerateWPAKeyFromWordExample Generated 160-bit WPA Key: " + generateWPAKeyFromWord(word, 20));
        logger.info("TestGenerateWPAKeyFromWordExample Generated 504-bit WPA Key: " + generateWPAKeyFromWord(word, 63));
>>>>>>> 1e30f501ab0857189871ea203db67a439334df0f
    }

    public static String generateHash(String input,String algoName) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algoName);
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    //Example hash Code : wdQHF6PLCG , h8K0mkKPA4 ,fqj4dCif3n ,CAUtJM3KwB ,JpMYJ4Op1z ,h8K0mkKPA4
    //Ref:  https://randomkeygen.com/ Memorable Passwords - Perfect for securing your computer or mobile device, or somewhere brute force is detectable.
    public static String generateCustomHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes).substring(0, 10);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateWEPKey(int length) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[length];
        random.nextBytes(key);
        StringBuilder hexString = new StringBuilder();
        for (byte b : key) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String generateWEPKeyFromString(String seed, int length) {
        SecureRandom random = new SecureRandom(seed.getBytes(StandardCharsets.UTF_8));
        byte[] key = new byte[length];
        random.nextBytes(key);
        StringBuilder hexString = new StringBuilder();
        for (byte b : key) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String generateFortKnoxHash(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }

    public static String generateFortKnoxHashFromWord(String seed, int length) {
        SecureRandom random = new SecureRandom(seed.getBytes(StandardCharsets.UTF_8));
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }

    public static String generateWPAKey(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[byteLength];
        secureRandom.nextBytes(key);

        StringBuilder hexString = new StringBuilder();
        for (byte b : key) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    public static String generateWPAKeyFromWord(String word, int byteLength) {
        byte[] seed = word.getBytes(StandardCharsets.UTF_8);
        SecureRandom secureRandom = new SecureRandom(seed);
        byte[] key = new byte[byteLength];
        secureRandom.nextBytes(key);

        StringBuilder hexString = new StringBuilder();
        for (byte b : key) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

}
