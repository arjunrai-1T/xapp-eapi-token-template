package org.xapp.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
public class RedisConfig {

    public static Logger logger = LogManager.getLogger(RedisConfig.class);

    @Value("${spring.redis.url}")
    private String redisUrl;

    @Value("${spring.redis.userid}")
    private String redisUserName;

    @Value("${spring.redis.password}")
    private String redisUserPassword;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        logger.info("redisConnectionFactory Entry");
        // Parse the URL to extract the host, port, and password
        String[] parts = redisUrl.replace("redis://", "").split("@");
        String[] userInfo = parts[0].split(":");
        String[] hostInfo = parts[1].split(":");
        String username = userInfo[0];
        String password = userInfo[1];
        String host = hostInfo[0];
        int port = Integer.parseInt(hostInfo[1]);
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setUsername(redisUserName);
        config.setPassword(redisUserPassword);
        RedisConnectionFactory  redisConnectionFactory = new LettuceConnectionFactory(config);
        logger.info("redisConnectionFactory redisConnectionFactory: {}",redisConnectionFactory.toString());
        logger.info("redisConnectionFactory Exit");
        return redisConnectionFactory;
    }
}

