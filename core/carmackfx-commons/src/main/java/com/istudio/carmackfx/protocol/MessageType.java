package com.istudio.carmackfx.protocol;

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

    public static MessageType valueOf(byte val) {
        return MessageType.values()[val];
    }

    @Override
    public String toString() {
        return String.valueOf(this.type);
    }
}
