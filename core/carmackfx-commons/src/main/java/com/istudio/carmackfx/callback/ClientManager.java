package com.istudio.carmackfx.callback;

/**
 * Created by ACA on 2017-6-11.
 */
public interface ClientManager {

    <T> T getCallback(Long sessionId, Class<T> clazz);
}
