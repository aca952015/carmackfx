package com.istudio.carmackfx.agent.processors;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.agent.MessageProcessor;
import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.exceptions.ActorException;
import com.istudio.carmackfx.interfaces.ActorService;
import com.istudio.carmackfx.interfaces.TokenService;
import com.istudio.carmackfx.model.consts.ErrorCodes;
import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.model.request.ActorMessageData;
import com.istudio.carmackfx.protocol.*;
import lombok.extern.slf4j.Slf4j;
import org.beykery.jkcp.KcpOnUdp;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@TProcessor(type = MessageType.ACTOR)
@Slf4j
public class ActorMessageProcessor implements MessageProcessor {

    @Autowired(required = false)
    private ActorService actorService;

    @Autowired(required = false)
    private TokenService tokenService;

    @Override
    public void init() {

    }

    @Override
    public MessageOut process(KcpOnUdp client, MessageIn msgIn) throws Exception {

        MessageOut msgOut = new MessageOut();
        msgOut.setId(msgIn.getId());
        msgOut.setMode(MessageConsts.MSG_ACTOR);
        msgOut.setToken(msgIn.getToken());

        try {

            if(actorService == null) {
                throw new ActorException(ErrorCodes.ACTOR_SERVICE_NOT_FOUND);
            }

            if(tokenService == null) {
                throw new ActorException(ErrorCodes.TOKEN_SERVICE_NOT_FOUND);
            }

            User user = tokenService.get(msgOut.getToken());
            if(user == null) {
                throw new ActorException(ErrorCodes.TOKEN_INVALID);
            }

            ActorMessageData request = JSON.parseObject(msgIn.getData(), ActorMessageData.class);
            if(request == null) {
                throw new ActorException(ErrorCodes.ACTOR_REQUEST_INVALID);
            }

            actorService.process(user, request);

            msgOut.setSuccess(MessageConsts.MSG_SUCCESS);

        } catch(ActorException e) {

            msgOut.setSuccess(MessageConsts.MSG_SERVERERROR);

            MessageErrorContent content = new MessageErrorContent();
            content.setErrorCode(e.getErrorCode());
            content.setErrorMessage(e.getMessage());

            return msgOut;
        } catch(Exception e) {
            throw e;
        }

        return msgOut;
    }
}
