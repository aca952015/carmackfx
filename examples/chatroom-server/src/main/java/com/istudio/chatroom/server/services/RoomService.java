package com.istudio.chatroom.server.services;

import com.istudio.carmackfx.agent.SessionManager;
import com.istudio.carmackfx.annotation.TContext;
import com.istudio.carmackfx.annotation.TMethod;
import com.istudio.carmackfx.annotation.TParam;
import com.istudio.carmackfx.callback.ClientManager;
import com.istudio.carmackfx.protocol.MessageContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ACA on 2017-6-10.
 */
@Component("RoomService")
@Slf4j
public class RoomService {

    @Autowired
    private SessionManager sessionManager;

    @TMethod("Join")
    public boolean join(@TContext MessageContext context) {

        log.info("user join {}", context.getUsername());

        String welcome = context.getUsername() + "进入聊天室";

        sessionManager.forEach((session) -> {

            ClientCallback client = session.getCallback(ClientCallback.class);
            if (client != null) {

                client.broadcast(welcome);
            }
        });

        return true;
    }

    @TMethod("Chat")
    public boolean chat(@TContext MessageContext context, @TParam("msg") String msg) {

        log.info("user chat {}", context.getUsername());

        sessionManager.forEach((session) -> {

            ClientCallback client = session.getCallback(ClientCallback.class);
            if (client != null) {

                client.broadcast(msg);
            }
        });

        return true;
    }

    @TMethod("Whisper")
    public boolean whisper(@TContext MessageContext context, @TParam("target") String target, @TParam("msg") String msg) {

        return true;
    }
}
