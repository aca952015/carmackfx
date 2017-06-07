package com.istudio.carmackfx.commons.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ACA on 2017-6-7.
 */
@Getter
@Setter
public class MessageOut {

    private int size;
    private int id;
    private byte success;
    private String data;
}
