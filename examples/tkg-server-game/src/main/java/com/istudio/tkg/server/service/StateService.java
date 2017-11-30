package com.istudio.tkg.server.service;

import com.istudio.carmackfx.annotation.TContext;
import com.istudio.carmackfx.annotation.TMethod;
import com.istudio.carmackfx.annotation.TPublic;
import com.istudio.carmackfx.protocol.MessageContext;
import com.istudio.carmackfx.protocol.MessageException;
import com.istudio.tkg.server.model.response.EnterGameResponse;
import com.istudio.tkg.server.repo.PlayerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("IStateService")
@TPublic
public class StateService {

    @Autowired
    private PlayerRepo playerRepo;

    @TMethod("EnterGame")
    public EnterGameResponse enterGame(@TContext MessageContext context) throws MessageException {

        log.info("user enter game");

        EnterGameResponse response = new EnterGameResponse();

        return response;
    }
}
