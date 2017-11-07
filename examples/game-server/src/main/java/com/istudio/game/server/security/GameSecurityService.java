package com.istudio.game.server.security;

import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.carmackfx.interfaces.AuthResult;

/**
 * Created by ACA on 2017/6/8.
 */
public class GameSecurityService implements SecurityService<GameAuthIn> {

    @Override
    public AuthResult auth(GameAuthIn authIn) {

        AuthResult result = new AuthResult();
        result.setUsername(authIn.getUsername());
        result.setSuccess(true);

        return result;
    }

    @Override
    public boolean verify(long token) {
        return false;
    }
}
