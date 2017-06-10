package com.istudio.carmackfx.protocol;

/**
 * Created by ACA on 2017-6-10.
 */
public enum MessageSuccess {

    SUCCESS(0),
    SERVERERROR(1),
    DATAINVALID(2);

    private int success;

    MessageSuccess(int success) {
        this.success = success;
    }

    public static MessageType valueOf(byte val) {
        return MessageType.values()[val];
    }

    @Override
    public String toString() {
        return String.valueOf(this.success);
    }
}
