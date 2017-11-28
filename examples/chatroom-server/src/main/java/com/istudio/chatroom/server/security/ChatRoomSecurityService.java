package com.istudio.chatroom.server.security;

import com.istudio.carmackfx.domain.User;
import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.carmackfx.domain.AuthResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by ACA on 2017/6/8.
 */
@Slf4j
@Component()
public class ChatRoomSecurityService implements SecurityService<ChatRoomAuthIn> {

    @Override
    public AuthResult auth(ChatRoomAuthIn authIn) {

        User user = new User();
        user.setUsername(authIn.getUsername());
        user.setNickname(authIn.getUsername());

        AuthResult result = new AuthResult();
        result.setSuccess(true);
        result.setUser(user);

        log.info("user login {}", authIn.getUsername());

        return result;
    }
}
