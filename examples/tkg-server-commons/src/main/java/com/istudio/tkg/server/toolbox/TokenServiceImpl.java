package com.istudio.tkg.server.toolbox;

import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.interfaces.TokenService;
import com.istudio.carmackfx.utils.IdUtils;
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
    private RedisTemplate<String, User> tokenRedisTemplate;

    @Override
    public long create(User user) {

        if(user != null && !StringUtils.isEmpty(user.getUsername())) {

            long token = IdUtils.gen().hashCode();
            String key = getKey(token);

            if(key == null) {
                return 0;
            }

            Object val = tokenRedisTemplate.execute(new SessionCallback<Object>() {
                @Override
                public  Object execute(RedisOperations operations) throws DataAccessException {

                    try {
                        operations.multi();
                        operations.opsForValue().set(key, user);
                        operations.expire(key, 1, TimeUnit.HOURS);
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

    private String getKey(Long token) {

        if(token == null) {
            return null;
        }

        return "token(" + token.toString() + ")";
    }

    @Override
    public boolean verify(long token) {

        return tokenRedisTemplate.hasKey(getKey(token));
    }

    @Override
    public User get(long token) {

        return tokenRedisTemplate.opsForValue().get(getKey(token));
    }
}
