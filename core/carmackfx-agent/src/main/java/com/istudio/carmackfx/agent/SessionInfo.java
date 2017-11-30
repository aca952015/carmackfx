package com.istudio.carmackfx.agent;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.beykery.jkcp.KcpOnUdp;

@Data
@AllArgsConstructor
public class SessionInfo {

    private long sessionId;
    private long token;
    private String id;
    private String nickname;
    private KcpOnUdp client;
}
