package org.xapp;

import io.jsonwebtoken.Claims;
import org.xapp.util.JwtUtil;

import javax.crypto.SecretKey;
import java.util.Date;
/*
    Use : https://randomkeygen.com/
    EAPI endpoint : getAccessToken
    Example fileds:
    {
        "client_id" :"UM1KBT3LD0", //May Be Web App: UM1KBT3LD0 or Mobile App: KBNVsZSC7r (N)
        "client_secret" :"UVHv4HTvAXhhVBcHlN2ydu7jXHNvJeDH", (N depend on client_id )
        "resource" :"C4592EDE7F4F7",
        "grant_type" :"client_credential",
    }
    Table Field:
    client_app_id                : App ID                           (System Generated)
    client_secret                : Secret Code                      (System Generated)
    client_app_type              : APP type WEB or MOBILE
    client_issuer                : User ID who registered this EAPI (System Generated given value)
    client_subject               : resource or Access Specifier Code what type of EAPI can consumed (System Generated)
*/
public class Main {

    public static void tokenOperation(){
        JwtUtil jwtUtil = new JwtUtil();
        // Create Token
        // Define your secret key (make sure it's at least 256 bits for HS512)
        // Replace with your actual key
        /*
                Use https://randomkeygen.com/ & CodeIgniter Encryption Keys - Can be used for any other 256-bit key requirement.
                Example Code:
                    3ZH355w8sOLFoUHukHU7TjNcdbHJoyZX
                    UVHv4HTvAXhhVBcHlN2ydu7jXHNvJeDH
                    2Umm0s9FNnoiVwN1mwLkJKB7T654GU9b
                    hCmh0kBHW2usD1Essy40hvNrw3o2e97S
        */
        //String client_secret_key    = "your-very-secure-key-that-is-at-least-64-bytes-long!";
        String client_id                    = "UM1KBT3LD0";                       //  getAccessToken client Id (Web or Mobile App)
        String client_secret                = "UVHv4HTvAXhhVBcHlN2ydu7jXHNvJeDH"; //  getAccessToken secret
        String client_issuer                = "Comcr0qlgI";                       //  Admin Code
        String client_subject               = "C4592EDE7F4F7";                    //  getAccessToken resource
        SecretKey secretKey = jwtUtil.createSecretKey(client_secret);
        // Store into database
        String    secretKeyStr = jwtUtil.encodeSecretKeyToString(secretKey);
        System.out.println("main Secrete Key String >>> "+ secretKeyStr);
        String jws_Token = jwtUtil.createToken(secretKey,client_id,client_issuer,client_subject);
        System.out.println("main EAPI token >>> "+ jws_Token);

        // Validate Token
        // Get SecretKey from database secretKey string
        SecretKey decodedSecretKey = jwtUtil.decodeStringToSecretKey(secretKeyStr);
        Claims claims = jwtUtil.validateTokenAndGetClaim(jws_Token, decodedSecretKey);
        if (claims != null) {
            System.out.println("main EAPI token is correct ");
            String subject  = claims.getSubject(); // Get subject or any other claim
            Date issuedAt   = claims.getIssuedAt(); // Get issued date
            Date expiration = claims.getExpiration(); // Get expiration date
            System.out.println("main Token ID:         " + claims.getId());
            System.out.println("main Token Issuer:     " + claims.getIssuer());
            System.out.println("main Token Subject:    " + claims.getSubject());
            System.out.println("main Token Issued At:  " + claims.getIssuedAt());
            System.out.println("main Token Expiration: " + claims.getExpiration());
        }else{
            System.out.println("main EAPI token is not correct ");
        }
    }

    public static void main(String[] args) {
        tokenOperation();
    }

}