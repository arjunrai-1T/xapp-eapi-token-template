package org.xapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xapp.entity.Token;
import org.xapp.repository.TokenRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    public static Logger logger = LogManager.getLogger(TokenService.class);

    @Autowired
    private TokenRepository tokenRepository;

    @Transactional
    public Token saveToken(String clientAppId, String token, String tokenIssuerID, LocalDateTime tokenIssuedAt,LocalDateTime tokenExpiry) {
        logger.info("Entry saveToken input clientAppId:{} ",clientAppId);
        logger.info("Entry saveToken input token:{} ",token);
        logger.info("Entry saveToken input tokenIssuerID:{} ",tokenIssuerID);
        logger.info("Entry saveToken input tokenIssuedAt:{} ",tokenIssuedAt);
        logger.info("Entry saveToken input tokenExpiry:{} ",tokenExpiry);
        Token newToken = new Token();
        newToken.setTokenId("TK_"+token.substring(0,6));
        newToken.setClientAppId(clientAppId);
        newToken.setToken(token);
        newToken.setTokenIssuerID(tokenIssuerID);
        newToken.setTokenIssuedAt(tokenIssuedAt);
        newToken.setTokenExpiry(tokenExpiry);
        logger.info("Entry saveToken input save and exit ");
        return tokenRepository.save(newToken);
    }

    public Optional<Token> getToken(String tokenId) {
        return tokenRepository.findById(tokenId);
    }

    public void deleteToken(String tokenId) {
        tokenRepository.deleteById(tokenId);
    }

}

