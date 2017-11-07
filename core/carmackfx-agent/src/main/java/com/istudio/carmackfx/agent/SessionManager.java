package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.utils.Pair;
import com.istudio.carmackfx.utils.PairSet;
import org.beykery.jkcp.KcpOnUdp;

/**
 * Created by ACA on 2017-6-10.
 */
public class SessionManager {

    private final PairSet<KcpOnUdp, SessionInfo> sessions = new PairSet<>();

    public void createSession(KcpOnUdp client, SessionInfo sessionInfo) {

        Pair<KcpOnUdp, SessionInfo> pair = new Pair<>(client, sessionInfo);

        sessions.add(pair);
    }

    public Pair<KcpOnUdp, SessionInfo> getSession(KcpOnUdp client) {

        SessionInfo sessionInfo = sessions.findRight(client);
        if(sessionInfo != null) {

            return new Pair<>(client, sessionInfo);
        }

        return null;
    }

    public void clearSession(KcpOnUdp kcp) {

        SessionInfo sessionInfo = sessions.findRight(kcp);
        if(sessionInfo != null) {

            Pair<KcpOnUdp, SessionInfo> pair = new Pair<>(kcp, sessionInfo);

            sessions.remove(pair);
        }
    }

    public boolean contains(KcpOnUdp client) {
        
        return sessions.contains(client);
    }
    
    public SessionInfo getValue(KcpOnUdp client) {
        
        if(!contains(client)) {
            return null;
        }
        
        return getSession(client).getRight();
    }
}
