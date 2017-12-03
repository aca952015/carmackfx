package com.istudio.tkg.server.service;

import com.istudio.carmackfx.actor.ActorManager;
import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.carmackfx.interfaces.TokenService;
import com.istudio.tkg.server.context.PlayerContext;
import com.istudio.tkg.server.model.request.AuthRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthService implements SecurityService<AuthRequest> {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ActorManager actorManager;

    @Override
    public User auth(AuthRequest request) {

        if(tokenService.verify(request.getToken())) {

            User user = tokenService.get(request.getToken());

            PlayerContext playerContext = actorManager.find(PlayerContext.class, user.getId());
            if(playerContext == null) {
                playerContext = actorManager.create(PlayerContext.class, user.getId());
                playerContext.init(user);
            } else {
                return null;
            }

            return user;
        }

        return null;
    }
}
