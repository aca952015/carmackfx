package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.model.consts.ErrorCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgentException extends Exception {

    private int errorCode;

    public AgentException(ErrorCodes errorCodes) {

        super(errorCodes.getMessage());

        this.errorCode = errorCodes.getCode();
    }

    public AgentException(int errorCode, String errorMessage) {

        super(errorMessage);

        this.errorCode = errorCode;
    }
}
