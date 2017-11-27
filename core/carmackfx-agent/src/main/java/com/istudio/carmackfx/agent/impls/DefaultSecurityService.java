package com.istudio.carmackfx.agent.impls;

import com.istudio.carmackfx.domain.AuthResult;
import com.istudio.carmackfx.interfaces.SecurityService;

public class DefaultSecurityService implements SecurityService<Void> {

    @Override
    public AuthResult auth(Void request) {

        AuthResult result = new AuthResult();
        result.setSuccess(false);

        return result;
    }
}
