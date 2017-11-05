package com.istudio.chatroom.server.auth;

import com.istudio.carmackfx.interfaces.AuthProvider;
import com.istudio.carmackfx.interfaces.AuthResult;

/**
 * Created by ACA on 2017/6/8.
 */
public class ChatRoomAuthProvider implements AuthProvider<ChatRoomAuthIn> {

    @Override
    public AuthResult verify(ChatRoomAuthIn authIn) {

        AuthResult result = new AuthResult();
        result.setUsername(authIn.getUsername());
        result.setSuccess(true);

        return result;
    }
}
