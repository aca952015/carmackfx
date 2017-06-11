package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.utils.Pair;
import com.istudio.carmackfx.utils.PairSet;
import org.beykery.jkcp.KcpOnUdp;

/**
 * Created by ACA on 2017-6-10.
 */
public class SessionManager {

    private final PairSet<Long, KcpOnUdp> sessions = new PairSet<>();

    public void createSession(Long token, KcpOnUdp client) {

        Pair<Long, KcpOnUdp> pair = new Pair<>(token, client);

        sessions.add(pair);
    }

    public Pair<Long, KcpOnUdp> getSession(KcpOnUdp client) {

        Long token = sessions.findLeft(client);
        if(token != null) {

            return new Pair<>(token, client);
        }

        return null;
    }

    public void clearSession(KcpOnUdp kcp) {

        Long token = sessions.findLeft(kcp);
        if(token != null) {

            Pair<Long, KcpOnUdp> pair = new Pair<>(token, kcp);

            sessions.remove(pair);
        }
    }
}
