package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.callback.ClientManager;
import com.istudio.carmackfx.utils.Function;
import com.istudio.carmackfx.utils.Pair;
import org.beykery.jkcp.KcpOnUdp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ACA on 2017-6-10.
 */
public class SessionManager {

    @Autowired
    private ClientManager clientManager;

    private final Map<Long, SessionInfo> sessions = new HashMap<>();
    private final Map<String, Long> socketMap = new HashMap<>();

    public void createSession(Long sessionId, SessionInfo sessionInfo) {

        if(sessions.containsKey(sessionId)) {

            clearSession(sessionId);
        }

        socketMap.put(sessionInfo.getClient().getRemote().toString(), sessionId);
        sessions.put(sessionId, sessionInfo);
    }

    public SessionInfo getSession(Long sessionId) {

        return sessions.get(sessionId);
    }

    public SessionInfo getSession(KcpOnUdp client) {

        if(contains(client)) {

            return getSession(socketMap.get(client.getRemote().toString()));
        }

        return null;
    }

    public void clearSession(Long sessionId) {

        SessionInfo sessionInfo = sessions.get(sessionId);
        if(sessionInfo != null) {

            Pair<Long, SessionInfo> pair = new Pair<>(sessionId, sessionInfo);

            sessions.remove(pair);
            socketMap.remove(sessionInfo.getClient().getRemote().toString());
        }
    }

    public void clearSession(KcpOnUdp client) {

        String socketKey = client.getRemote().toString();
        if(socketMap.containsKey(socketKey)) {

            Long sessionId = socketMap.get(socketKey);

            sessions.remove(sessionId);
            socketMap.remove(socketKey);
        }
    }

    public boolean contains(Long sessionId) {
        
        return sessions.containsKey(sessionId);
    }

    public boolean contains(KcpOnUdp client) {

        return socketMap.containsKey(client.getRemote().toString());
    }

    public void forEach(Function<SessionItem> func) {

        for(SessionInfo info : sessions.values()) {

            SessionItem item = new SessionItem(clientManager, info);

            func.invoke(item);
        }
    }
}
