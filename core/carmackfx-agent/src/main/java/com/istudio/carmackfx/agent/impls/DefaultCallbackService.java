package com.istudio.carmackfx.agent.impls;

import com.istudio.carmackfx.agent.AgentServer;
import com.istudio.carmackfx.agent.SessionInfo;
import com.istudio.carmackfx.agent.SessionManager;
import com.istudio.carmackfx.callback.CallbackService;
import com.istudio.carmackfx.protocol.MessageConsts;
import com.istudio.carmackfx.protocol.MessageOut;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultCallbackService implements CallbackService {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private AgentServer agentServer;

    @Override
    public void send(Long sessionId, MessageOut msgOut) {

        if(sessionManager.contains(sessionId)) {

            SessionInfo info = sessionManager.getSession((sessionId));

            msgOut.setId(-1);
            msgOut.setMode(MessageConsts.MSG_CALLBACK);
            msgOut.setSuccess(MessageConsts.MSG_SUCCESS);
            msgOut.setToken(info.getToken());

            agentServer.send(info.getClient(), msgOut);
        }
    }
}
