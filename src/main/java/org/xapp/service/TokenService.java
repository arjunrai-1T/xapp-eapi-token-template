package org.xapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xapp.entity.Token;
import org.xapp.repository.TokenRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Transactional
    public Token saveToken(String clientAppId, String token, LocalDateTime tokenExpiry) {
        Token newToken = new Token();
        newToken.setClientAppId(clientAppId);
        newToken.setToken(token);
        newToken.setTokenExpiry(tokenExpiry);
        return tokenRepository.save(newToken);
    }

    public Optional<Token> getToken(String tokenId) {
        return tokenRepository.findById(tokenId);
    }

    public void deleteToken(String tokenId) {
        tokenRepository.deleteById(tokenId);
    }

}

