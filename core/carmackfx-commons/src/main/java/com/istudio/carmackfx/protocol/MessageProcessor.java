package com.istudio.carmackfx.protocol;

/**
 * Created by ACA on 2017-6-7.
 */
public interface MessageProcessor {

    MessageOut process(MessageIn msgIn);
}
