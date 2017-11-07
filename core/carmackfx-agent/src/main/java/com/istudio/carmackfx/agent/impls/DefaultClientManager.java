package com.istudio.carmackfx.agent.impls;

import com.istudio.carmackfx.callback.ClientManager;

/**
 * Created by ACA on 2017-6-11.
 */
public class DefaultClientManager implements ClientManager {

    @Override
    public <T> T getCallback(Long token, Class<T> clazz) {
        return null;
    }
}
