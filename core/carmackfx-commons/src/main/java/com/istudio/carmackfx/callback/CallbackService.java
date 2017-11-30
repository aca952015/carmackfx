package com.istudio.carmackfx.callback;

import com.istudio.carmackfx.annotation.TService;
import com.istudio.carmackfx.protocol.MessageOut;

@TService
public interface CallbackService {

    void send(Long sessionId, MessageOut msgOut);
}
