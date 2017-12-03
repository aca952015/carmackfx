package com.istudio.carmackfx.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@Data
public class ActorMessageData {

    private String actor;
    private String message;
    private List<String> arguments;
}
