package com.istudio.tkg.server.context;

import com.istudio.carmackfx.actor.CarmackContext;
import com.istudio.carmackfx.model.domain.User;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
public interface PlayerContext extends CarmackContext {

    void init(User user);
}
