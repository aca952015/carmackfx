package com.istudio.chatroom.server.security;

import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.interfaces.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by ACA on 2017/6/8.
 */
@Slf4j
@Component()
public class ChatRoomSecurityService implements SecurityService<ChatRoomAuthIn> {

    @Override
    public User auth(ChatRoomAuthIn authIn) {

        User user = new User();
        user.setId(authIn.getUsername());
        user.setNickname(authIn.getUsername());

        log.info("user login {}", authIn.getUsername());

        return user;
    }
}
