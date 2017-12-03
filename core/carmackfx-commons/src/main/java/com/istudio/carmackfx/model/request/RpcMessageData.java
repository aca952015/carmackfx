package com.istudio.carmackfx.model.request;

import lombok.Data;

import java.util.Map;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@Data
public class RpcMessageData {

    private String serviceName;
    private String methodName;
    private Map<String, RpcMessageArgument> arguments;
}
