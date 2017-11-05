package com.istudio.carmackfx.agent.processors;

import com.istudio.carmackfx.agent.MessageProcessor;
import com.istudio.carmackfx.agent.SessionManager;
import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.interfaces.AuthService;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.protocol.MessageType;
import org.beykery.jkcp.KcpOnUdp;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ACA on 2017/6/8.
 */
@TProcessor(type = MessageType.AUTH)
public class AuthMessageProcessor implements MessageProcessor {

    @Autowired(required = false)
    private AuthService authService;

    @Autowired
    private SessionManager sessionManager;

    @Override
    public MessageOut process(KcpOnUdp client, MessageIn msgIn) {

        if(authService == null) {

            return null;
        }

        MessageOut msgOut = authService.verify(msgIn);
        if(msgOut.getSuccess() == 0 && msgOut.getToken() > 0L) {

            sessionManager.createSession(msgIn.getToken(), client);
        }

        return msgOut;
    }
}
