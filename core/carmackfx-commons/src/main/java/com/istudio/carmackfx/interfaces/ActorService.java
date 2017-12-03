package com.istudio.carmackfx.interfaces;

import com.istudio.carmackfx.exceptions.ActorException;
import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.model.request.ActorMessageData;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
public interface ActorService {

    void process(User user, ActorMessageData request) throws ActorException;
}
