package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.interfaces.AuthService;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.protocol.MessageProcessor;
import com.istudio.carmackfx.protocol.MessageType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ACA on 2017/6/8.
 */
@TProcessor(type = MessageType.AUTH)
public class AuthMessageProcessor implements MessageProcessor {

    @Autowired(required = false)
    private AuthService authService;

    @Override
    public MessageOut process(MessageIn msgIn) {

        if(authService == null) {

            return null;
        }

        return authService.verify(msgIn);
    }
}
