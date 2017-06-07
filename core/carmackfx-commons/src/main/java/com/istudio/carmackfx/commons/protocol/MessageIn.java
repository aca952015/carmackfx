package com.istudio.carmackfx.commons.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ACA on 2017-6-7.
 */
@Getter
@Setter
public class MessageIn {

    private long id;
    private int size;
    private MessageType type;
    private String token;
    private String data;
}
