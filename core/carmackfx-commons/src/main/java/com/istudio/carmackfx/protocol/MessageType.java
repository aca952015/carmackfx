package com.istudio.carmackfx.protocol;

/**
 * Created by ACA on 2017/6/7.
 */
public enum MessageType {

    HEARTBEAT(0),
    SECURITY(1),
    INTERNAL(2),
    PUBLIC(3),
    ACTOR(4);

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
