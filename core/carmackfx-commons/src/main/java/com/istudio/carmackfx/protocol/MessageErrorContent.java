package com.istudio.carmackfx.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ACA on 2017-6-13.
 */
@Getter
@Setter
public class MessageErrorContent {

    private int errorCode;
    private String errorMessage;
}
