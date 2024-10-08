package org.xapp.startup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.xapp.service.TokenService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import org.xapp.entity.Token;
import org.xapp.util.JwtUtil;

import javax.crypto.SecretKey;

@Component
public class ExperienceXAppTokenListener implements ApplicationListener<ApplicationReadyEvent> {

    private static String client_secret   = "UVHv4HTvAXhhVBcHlN2ydu7jXHNvJeDH";

    public static Logger logger = LogManager.getLogger(ExperienceXAppTokenListener.class);

    @Autowired
    public TokenService tokenService;

    public String tokenOperation(String client_id,String client_issuer,String client_subject) {
        JwtUtil jwtUtil = new JwtUtil();
        SecretKey secretKey = jwtUtil.createSecretKey(ExperienceXAppTokenListener.client_secret);
        // Store into database
        String secretKeyStr = jwtUtil.encodeSecretKeyToString(secretKey);
        logger.info("tokenOperation Secrete Key String >>> " + secretKeyStr);
        String jws_Token = jwtUtil.createToken(secretKey, client_id, client_issuer, client_subject);
        logger.info("tokenOperation EAPI token >>> " + jws_Token);
        return jws_Token;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Entry ExperienceXAppTokenListener Application is fully ready!");
        long EXPIRATION_TIME = 1000 * 60 * 60;
        String client_id       = "UM1KBT3LD0";                        //  getAccessToken client Id (Web or Mobile App ID in Hex)
        String client_issuer   = "Comcr0qlgI";                        //  User ID
        String client_subject  = "C4592EDE7F4F7";
        String tokenInfo =  tokenOperation(client_id, client_issuer,client_subject);
        //Token issue at
        Date dateIssuedAt = new Date(System.currentTimeMillis());
        Instant instantIssuedAt = dateIssuedAt.toInstant();
        LocalDateTime localDateTimeIssuedAt = LocalDateTime.ofInstant(instantIssuedAt, ZoneId.systemDefault());
        logger.info("ExperienceXAppTokenListener localDateTimeIssuedAt: {}",localDateTimeIssuedAt);
        //Token Expiry
        Date dateExpiry = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        Instant instantExpiry = dateExpiry.toInstant();
        LocalDateTime localDateTimeExpiry = LocalDateTime.ofInstant(instantExpiry, ZoneId.systemDefault());
        logger.info("ExperienceXAppTokenListener localDateTimeExpiry: {}",localDateTimeExpiry);

        LocalDateTime nowDateAndTime = LocalDateTime.now();
        LocalDateTime expirationDateAndTime = nowDateAndTime.plusMinutes(EXPIRATION_TIME);

        //Token issuer userid
        String tokenIssuerID= client_issuer;
        String clientAppId = client_id;

        Token token = tokenService.saveToken(clientAppId,tokenInfo,tokenIssuerID,localDateTimeIssuedAt,localDateTimeExpiry);
        Optional<Token> storeToken= tokenService.getToken(token.getTokenId());
        logger.info("get storeToken Token Info: {}",storeToken.get());

        tokenInfo="";
        tokenInfo =  tokenOperation(client_id, client_issuer,client_subject);
        token = null;
        token = tokenService.saveToken(clientAppId,tokenInfo,tokenIssuerID,localDateTimeIssuedAt,localDateTimeExpiry);
        storeToken = null;
        storeToken= tokenService.getToken(token.getTokenId());
        logger.info("get storeToken Token Info: {}",storeToken.get());

        logger.info("Exit ExperienceXAppTokenListener");
    }
}
