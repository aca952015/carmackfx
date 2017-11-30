package com.istudio.carmackfx.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ACA on 2017-6-7.
 */
@Getter
@Setter
public class MessageOut {

    private int size;
    private long id;
    private byte mode;              // 0: heartbeat, 1: result, 2: callback, 3: error
    private byte success;           // 0: true, 1: false
    private long token;             // security token
    private String data;            // json
}
