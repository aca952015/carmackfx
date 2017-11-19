package com.istudio.chatroom.server.services;

import com.istudio.carmackfx.annotation.TCallback;
import com.istudio.carmackfx.annotation.TMethod;

/**
 * Created by ACA on 2017-6-11.
 */
@TCallback
public interface ClientCallback {

    @TMethod("Broadcast")
    void broadcast(String content);

    @TMethod("Whisper")
    void whisper(String from, String content);
}
