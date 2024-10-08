package org.xapp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class JwtUtil {

    public static Logger logger = LogManager.getLogger(JwtUtil.class);

    // Define your secret key (make sure it's at least 256 bits for HS512)
    // String baseKey = "your-very-secure-key-that-is-at-least-64-bytes-long!"; // Replace with your actual key
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    //Use https://randomkeygen.com/ to generate Encryption Keys
    public byte[] generateHashFromString(String input) {
        try {
            // Create a MessageDigest instance for SHA-512
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Generate the hash
            return digest.digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No algorithm not found", e);
        }catch (Exception ex) {
            throw new RuntimeException("Error creating secret key" + ex.getMessage());
        }
    }

    public SecretKey createSecretKey(String secretKey) {
        try {
            // Create a SHA-256 hash of the key
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedKey = digest.digest(secretKey.getBytes(StandardCharsets.UTF_8));
            // If you need to make it 64 bytes, you can concatenate or use a larger hash
            byte[] keyBytes = new byte[64];
            System.arraycopy(hashedKey, 0, keyBytes, 0, Math.min(hashedKey.length, keyBytes.length));
            // Create the SecretKey from the byte array
            return new SecretKeySpec(keyBytes, SignatureAlgorithmCustom.HS512.getJcaName());
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("No algorithm not found"+ ex.getMessage());
        }catch (Exception ex) {
            throw new RuntimeException("Error creating secret key" + ex.getMessage());
        }
    }

    public String encodeSecretKeyToString(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public SecretKey decodeStringToSecretKey(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        // SignatureAlgorithm.HS512.getJcaName()
        // return new SecretKeySpec(decodedKey,"HmacSHA512");
        return new SecretKeySpec(decodedKey,SignatureAlgorithmCustom.HS512.getJcaName() );
    }

    public String createToken(SecretKey key,String id,String issuer,String subject) {
        String jws_Token="";
        // Create a test key suitable for the desired HMAC-SHA algorithm:
        MacAlgorithm alg = Jwts.SIG.HS512;
        long nowMillis = System.currentTimeMillis();
        logger.info("validateTokenAndClaim nowMillis: {}",nowMillis);
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            logger.info("validateTokenAndClaim Thread.sleep InterruptedException");
        }
        long afterMillis = System.currentTimeMillis();
        logger.info("validateTokenAndClaim afterMillis: {}",afterMillis);
        Date issuedDate     = new Date(nowMillis);
        Date expirationDate = new Date(afterMillis + EXPIRATION_TIME);
        logger.info("token start Date & Time Stamp:{} " ,issuedDate);
        logger.info("token end   Date & Time Stamp:{} " ,expirationDate);
        try{
            jws_Token = Jwts.builder()
                         .id(id) // Web or Mobile App Id
                         .issuer(issuer)
                         .subject(subject)
                         .issuedAt(issuedDate) //Current Date
                         .expiration(expirationDate) //Current Date with 1 Hour
                         .signWith(key, alg).compact();
            return jws_Token;
        } catch (io.jsonwebtoken.security.InvalidKeyException ex) {
            logger.info("validateTokenAndClaim IllegalArgumentException: " + ex.getMessage());
            return null;
        } catch (io.jsonwebtoken.JwtException ex) {
            logger.info("validateTokenAndClaim JwtException: " + ex.getMessage());
            return null;
        }catch (Exception ex) {
            logger.info("validateTokenAndClaim Exception: " + ex.getMessage());
            return null;
        }
    }

    public  Claims validateTokenAndGetClaim(String token, SecretKey key) {
        try {
            // Validate the token and return the claims
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token) // Returns Claims
                    .getPayload();
        } catch (io.jsonwebtoken.JwtException ex) {
            logger.info("validateTokenAndClaim JwtException: " + ex.getMessage());
            return null;
        } catch (IllegalArgumentException ex) {
            logger.info("validateTokenAndClaim IllegalArgumentException: " + ex.getMessage());
            return null;
        } catch (Exception ex) {
            logger.info("validateTokenAndClaim Exception: " + ex.getMessage());
            return null;
        }
    }

}
