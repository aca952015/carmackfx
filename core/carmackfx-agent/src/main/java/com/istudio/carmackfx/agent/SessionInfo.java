package com.istudio.carmackfx.agent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beykery.jkcp.KcpOnUdp;

@Getter
@Setter
@AllArgsConstructor
public class SessionInfo {

    private String sessionId;
    private long token;
    private String username;
    private String nickname;
    private KcpOnUdp client;
}
