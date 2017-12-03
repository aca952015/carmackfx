package com.istudio.tkg.server.actor;

import com.istudio.carmackfx.actor.CarmackActor;
import com.istudio.carmackfx.model.domain.User;
import com.istudio.tkg.server.model.domain.Player;
import com.istudio.tkg.server.repo.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@Component("PlayerContext")
@Scope("prototype")
public class PlayerActor extends CarmackActor implements com.istudio.tkg.server.context.PlayerContext {

    @Autowired
    private PlayerRepo playerRepo;

    private User user;
    private Player player;

    public void init(User user) {

        this.user = user;
        this.player = playerRepo.findByOwner(user.getId());
    }
}
