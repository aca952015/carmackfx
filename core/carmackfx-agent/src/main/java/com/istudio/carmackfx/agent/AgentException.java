package com.istudio.carmackfx.agent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentException extends Exception {

    private int errorCode;

    public AgentException(int errorCode, String errorMessage) {

        super(errorMessage);

        this.errorCode = errorCode;
    }
}
