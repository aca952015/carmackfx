package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.callback.ClientManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SessionItem {

    private ClientManager clientManager;
    private SessionInfo info;

    public <T> T getCallback(Class<T> clazz) {

        return clientManager.getCallback(info.getSessionId(), clazz);
    }
}
