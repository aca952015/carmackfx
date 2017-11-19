package com.istudio.carmackfx.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ACA on 2017-6-11.
 */
@Getter
@Setter
public class MessageContext {

    private String sessionId;
    private long token;
    private String username;
    private String nickname;
}
