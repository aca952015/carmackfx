package com.istudio.tkg.server;

import com.istudio.carmackfx.model.domain.User;
import com.istudio.tkg.server.utils.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CommonsConfig {

    @Bean
    RedisTemplate<String, User> tokenRedisTemplate(RedisConnectionFactory factory) {

        RedisTemplate redisTemplate = new RedisTemplate<String, User>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new RedisObjectSerializer(User.class));

        return redisTemplate;
    }
}
