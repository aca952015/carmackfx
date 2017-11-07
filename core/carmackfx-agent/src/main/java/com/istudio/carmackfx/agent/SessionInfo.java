package com.istudio.carmackfx.agent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionInfo {

    private String sessionId;
    private long token;
    private String username;
}
