package com.istudio.carmackfx.exceptions;

import com.istudio.carmackfx.model.consts.ErrorCodes;
import lombok.Getter;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@Getter
public class ActorException extends Exception {

    private final int errorCode;

    public ActorException(ErrorCodes code) {

        this.errorCode = code.getCode();
    }
}
