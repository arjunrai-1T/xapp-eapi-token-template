package org.xapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xapp.entity.Token;
import org.xapp.repository.TokenRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class TokenService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int RANDOM_LENGTH = 8; // Length of the random part

    public static Logger logger = LogManager.getLogger(TokenService.class);

    @Autowired
    private TokenRepository tokenRepository;

    @Transactional
    @CachePut(value = "tokens", key = "#result.tokenId")
    public Token saveToken(String clientAppId, String token, String tokenIssuerID, LocalDateTime tokenIssuedAt,LocalDateTime tokenExpiry) {
        logger.info("Entry saveToken input clientAppId:{} ",clientAppId);
        logger.info("saveToken input token:{} ",token);
        logger.info("saveToken input tokenIssuerID:{} ",tokenIssuerID);
        logger.info("saveToken input tokenIssuedAt:{} ",tokenIssuedAt);
        logger.info("saveToken input tokenExpiry:{} ",tokenExpiry);
        String uniqueTokenId = generateUniqueTokenId(token);
        logger.info("saveToken input tokenId:{} ",uniqueTokenId);
        String tokenId = "TK_"+uniqueTokenId;
        logger.info("saveToken input tokenId:{} ",tokenId);
        Token newToken = Token.builder()
                        .tokenId(tokenId)
                        .clientAppId(clientAppId)
                        .token(token)
                        .tokenIssuerID(tokenIssuerID)
                        .tokenIssuedAt(tokenIssuedAt)
                        .tokenExpiry(tokenExpiry).build();
        logger.info("Exit saveToken save new record");
        return tokenRepository.save(newToken);
    }

    @Transactional
    @Cacheable(value = "tokens", key = "#root.args")
    public Optional<Token> getToken(String tokenId) {
        if (tokenId == null) {
            logger.warn("getToken Attempted to fetch token with a null tokenId");
            return Optional.empty(); // Or throw an IllegalArgumentException
        }
        return Optional.ofNullable(tokenRepository.findById(tokenId).orElse(null));
    }

    @Transactional
    @CacheEvict(value = "tokens", key = "#tokenId")
    public void deleteToken(String tokenId) {
        tokenRepository.deleteById(tokenId);
    }

    private static String generateUniqueTokenId(String jwt) {
        // If 'jti' doesn't exist, generate a new unique ID
        return generateRandomStringWithDateTime();
    }

    private static String generateRandomStringWithDateTime() {
        // Get current date and time in a specific format, including milliseconds
        String dateTimePart = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        // Generate random string part
        String randomPart = generateRandomString(RANDOM_LENGTH);
        // Combine date time part with random part
        return randomPart+dateTimePart ;
    }

    private static String generateRandomString(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    private static String generateNewUniqueId() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[24]; // 24 bytes = 192 bits
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

}

