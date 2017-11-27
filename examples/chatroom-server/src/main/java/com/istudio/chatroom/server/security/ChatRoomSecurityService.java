package com.istudio.chatroom.server.security;

import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.carmackfx.domain.AuthResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by ACA on 2017/6/8.
 */
@Slf4j
public class ChatRoomSecurityService implements SecurityService<ChatRoomAuthIn> {

    @Override
    public AuthResult auth(ChatRoomAuthIn authIn) {

        AuthResult result = new AuthResult();
        result.setUsername(authIn.getUsername());
        result.setNickname(authIn.getUsername());
        result.setSuccess(true);
        result.setToken(authIn.getUsername().hashCode());

        log.info("user login {}", authIn.getUsername());

        return result;
    }

    @Override
    public boolean verify(long token) {
        return false;
    }
}
