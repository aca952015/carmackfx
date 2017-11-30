package com.istudio.carmackfx.agent.impls;

import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.interfaces.TokenService;

import java.util.HashMap;

public class DefaultTokenService implements TokenService {

    private static HashMap<Long, User> map = new HashMap<>();

    @Override
    public long create(User user) {

        long token = user.getId().hashCode();
        map.put(token, user);

        return token;
    }

    @Override
    public boolean verify(long token) {

        return map.containsKey(token);
    }

    @Override
    public User get(long token) {

        return map.get(token);
    }
}
