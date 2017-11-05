package com.istudio.chatroom.server.services;

import com.istudio.carmackfx.annotation.TContext;
import com.istudio.carmackfx.annotation.TService;
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
@TService
@Component("RoomService")
@Slf4j
public class RoomService {

    @Autowired
    private ClientManager clientManager;

    private final Map<Long, String> onlineUsers = new HashMap<>();

    public void join(@TContext MessageContext context) {

        log.info("user join {}", context.getUsername());

        onlineUsers.put(context.getToken(), context.getUsername());

        String welcome = context.getUsername() + "进入聊天室";

        for (Long token : onlineUsers.keySet()) {

            ClientCallback client = clientManager.getCallback(token, ClientCallback.class);
            if(client != null) {

                client.broadcast(welcome);
            }
        }
    }
}
