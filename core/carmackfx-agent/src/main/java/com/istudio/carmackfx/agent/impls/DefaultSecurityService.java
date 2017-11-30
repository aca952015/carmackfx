package com.istudio.carmackfx.agent.impls;

import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.model.response.AuthResponse;
import com.istudio.carmackfx.interfaces.SecurityService;

public class DefaultSecurityService implements SecurityService<Void> {

    @Override
    public User auth(Void request) {

        return null;
    }
}
