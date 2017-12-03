package com.istudio.carmackfx.actor;

import akka.actor.ActorRef;
import com.istudio.carmackfx.exceptions.ActorException;
import com.istudio.carmackfx.interfaces.ActorService;
import com.istudio.carmackfx.model.consts.ErrorCodes;
import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.model.request.ActorMessageData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorManager actorManager;

    @Override
    public void process(User user, ActorMessageData request) throws ActorException {

        try {

            ActorRef ref = actorManager.query(request.getActor(), user.getId());

            ActorMessage message = new ActorMessage();
            message.setName(request.getMessage());
            message.setArguments(new ArrayList<>(message.getArguments()));

            ref.tell(message, null);
        } catch (Exception e) {
            throw new ActorException(ErrorCodes.ACTOR_OUNT_FOUND);
        }
    }
}
