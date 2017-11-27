package com.istudio.tkg.server;

import com.istudio.carmackfx.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CommonsConfig {

    @Bean
    RedisTemplate redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate redisTemplate = new RedisTemplate<Long, User>();
        redisTemplate.setConnectionFactory(factory);

        return redisTemplate;
    }
}
