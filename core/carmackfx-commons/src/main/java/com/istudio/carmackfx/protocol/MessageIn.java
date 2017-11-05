package com.istudio.carmackfx.protocol;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

/**
 * Created by ACA on 2017-6-7.
 */
@Getter
@Setter
public class MessageIn {

    private long id;
    private int size;
    private MessageType type;
    private long token;
    private String data;
}
