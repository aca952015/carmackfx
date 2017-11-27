package com.istudio.tkg.server.toolbox;

import com.istudio.carmackfx.domain.User;
import com.istudio.carmackfx.interfaces.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisTemplate<Long, User> redisTemplate;

    @Override
    public long create(User user) {

        if(user != null && StringUtils.isEmpty(user.getUsername())) {

            long token = user.getUsername().hashCode();

            Object val = redisTemplate.execute(new SessionCallback<Object>() {
                @Override
                public  Object execute(RedisOperations operations) throws DataAccessException {

                    try {
                        operations.multi();
                        operations.opsForValue().set(token, user);
                        operations.expire(token, 1, TimeUnit.HOURS);
                        return operations.exec();
                    } catch (Exception e) {
                        return null;
                    }
                }
            });

            if(val != null) {

                return token;
            }
        }
        return 0;
    }

    @Override
    public boolean verify(long token) {

        return redisTemplate.hasKey(token);
    }

    @Override
    public User get(long token) {

        return redisTemplate.opsForValue().get(token);
    }
}
