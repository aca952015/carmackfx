package com.istudio.game.server.auth;

import com.istudio.carmackfx.interfaces.AuthProvider;
import com.istudio.carmackfx.interfaces.AuthResult;

/**
 * Created by ACA on 2017/6/8.
 */
public class GameAuthProvider implements AuthProvider<GameAuthIn> {

    @Override
    public AuthResult verify(GameAuthIn authIn) {

        AuthResult result = new AuthResult();
        result.setUsername(authIn.getUsername());
        result.setSuccess(true);

        return result;
    }
}
