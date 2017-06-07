package com.istudio.carmackfx.commons.protocol;

/**
 * Created by ACA on 2017/6/7.
 */
public enum MessageType {

    HEARTBEAT(0),
    AUTH(1),
    GAME(2);

    private int type;

    MessageType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(this.type);
    }
}
