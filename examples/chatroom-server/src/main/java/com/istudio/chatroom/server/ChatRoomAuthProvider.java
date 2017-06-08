package com.istudio.chatroom.server;

import com.istudio.carmackfx.interfaces.AuthProvider;

/**
 * Created by ACA on 2017/6/8.
 */
public class ChatRoomAuthProvider implements AuthProvider<ChatRoomAuthIn, ChatRoomAuthOut> {

    @Override
    public ChatRoomAuthOut verify(ChatRoomAuthIn authIn) {
        return null;
    }
}
