package com.istudio.carmackfx.actor;

import akka.actor.ActorRef;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@AllArgsConstructor
@Getter
public class ActorInstance {

    private ActorRef actorRef;

    public void tell(String name, List<Object> args) {

        ActorMessage message = new ActorMessage();
        message.setName(name);
        message.setArguments(args);

        actorRef.tell(message, null);
    }
}
