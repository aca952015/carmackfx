package com.istudio.carmackfx.agent.impls;

import com.istudio.carmackfx.interfaces.AuthResult;
import com.istudio.carmackfx.interfaces.SecurityService;

public class DefaultSecurityService implements SecurityService<Void> {

    @Override
    public AuthResult auth(Void authData) {

        AuthResult result = new AuthResult();
        result.setSuccess(true);

        return result;
    }

    @Override
    public boolean verify(long token) {
        return true;
    }
}
