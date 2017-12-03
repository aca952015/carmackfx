package com.istudio.carmackfx.actor;

import lombok.Data;

import java.util.List;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@Data
public class ActorMessage {

    private String name;
    private List<Object> arguments;
}