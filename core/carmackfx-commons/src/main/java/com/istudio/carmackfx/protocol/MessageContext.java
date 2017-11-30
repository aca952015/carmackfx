package com.istudio.carmackfx.protocol;

import lombok.Data;

/**
 * Created by ACA on 2017-6-11.
 */
@Data
public class MessageContext {

    private long sessionId;
    private long token;
    private String id;
    private String nickname;
}
