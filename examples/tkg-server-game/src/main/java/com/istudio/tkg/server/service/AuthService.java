package com.istudio.tkg.server.service;

import com.istudio.carmackfx.domain.AuthResult;
import com.istudio.carmackfx.domain.User;
import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.carmackfx.interfaces.TokenService;
import com.istudio.tkg.server.model.dto.AuthRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthService implements SecurityService<AuthRequest> {

    @Autowired
    private TokenService tokenService;

    @Override
    public AuthResult auth(AuthRequest request) {

        AuthResult result = new AuthResult();

        if(tokenService.verify(request.getToken())) {

            result.setSuccess(true);
            result.setUser(tokenService.get(request.getToken()));
        }

        return result;
    }
}
