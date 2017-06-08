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
    private byte success;
    private String data;
}
