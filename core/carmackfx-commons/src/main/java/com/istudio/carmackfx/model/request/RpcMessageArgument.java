package com.istudio.carmackfx.model.request;

import lombok.Data;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@Data
public class RpcMessageArgument {

    private String argumentName;
    private String argumentValue;
    private boolean isValueType;
}
