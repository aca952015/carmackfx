package com.istudio.carmackfx.agent;

public enum ErrorCodes {

    SERVER_ERROR("SERVER_ERROR", 1000),
    SERVICE_NOT_FOUND("SERVICE_NOT_FOUND", 1001),
    METHOD_NOT_FOUND("METHOD_NOT_FOUND", 1002),
    ILLEGAL_ARGUMENT("ILLEGAL_ARGUMENT", 1003),
    BUSINESS_ERROR("BUSINESS_ERROR", 1004),
    SERVICE_NOT_PUBLIC("SERVICE_NOT_PUBLIC", 1005),
    TOKEN_INVALID("TOKEN_INVALID", 1006);

    private String message;
    private int code;

    ErrorCodes(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public static String getMessage(int value) {
        for (ErrorCodes c : ErrorCodes.values()) {
            if (c.getCode() == value) {
                return c.message;
            }
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
