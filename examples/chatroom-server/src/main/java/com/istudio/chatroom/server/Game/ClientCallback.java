package com.istudio.chatroom.server.Game;

import com.istudio.carmackfx.annotation.TCallback;

/**
 * Created by ACA on 2017-6-11.
 */
@TCallback
public interface ClientCallback {

    void broadcast(String content);
    void whisper(String from, String content);
}
