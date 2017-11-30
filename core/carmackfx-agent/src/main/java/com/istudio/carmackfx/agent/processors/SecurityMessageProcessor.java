package com.istudio.carmackfx.agent.processors;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.agent.MessageProcessor;
import com.istudio.carmackfx.agent.SessionInfo;
import com.istudio.carmackfx.agent.SessionManager;
import com.istudio.carmackfx.agent.impls.DefaultSecurityService;
import com.istudio.carmackfx.agent.impls.DefaultTokenService;
import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.carmackfx.interfaces.TokenService;
import com.istudio.carmackfx.model.domain.User;
import com.istudio.carmackfx.model.response.AuthResponse;
import com.istudio.carmackfx.protocol.MessageConsts;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.protocol.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.beykery.jkcp.KcpOnUdp;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ACA on 2017/6/8.
 */
@TProcessor(type = MessageType.SECURITY)
@Slf4j
public class SecurityMessageProcessor implements MessageProcessor {

    @Autowired(required = false)
    private SecurityService securityService;

    @Autowired(required = false)
    private TokenService tokenService;

    @Autowired
    private SessionManager sessionManager;

    private Class<?> authParameterType;
    private boolean isVoidParameterType;

    @Override
    public void init() {

        if (securityService == null) {

            securityService = new DefaultSecurityService();
            authParameterType = Void.class;
        }

        if (authParameterType == null) {

            authParameterType = getAuthParameterType();
        }

        if(tokenService == null) {

            tokenService = new DefaultTokenService();
        }

        if(authParameterType.getName().equals("System.lang.Void")) {
            isVoidParameterType = true;
        }
    }

    @Override
    public MessageOut process(KcpOnUdp client, MessageIn msgIn) {

        try {

            Object argu = null;

            if(isVoidParameterType == false) {
                argu = JSON.parseObject(msgIn.getData(), authParameterType);
            }

            MessageOut msgOut = new MessageOut();
            msgOut.setId(msgIn.getId());
            msgOut.setSuccess(MessageConsts.MSG_SUCCESS);
            msgOut.setMode(MessageConsts.MSG_RESULT);

            User user = securityService.auth(argu);

            AuthResponse response = new AuthResponse();
            if(user != null) {

                response.setSuccess(true);

                long token = tokenService.create(user);

                SessionInfo sessionInfo = new SessionInfo(
                        user.getId().hashCode(),
                        token,
                        user.getId(),
                        user.getNickname(),
                        client
                );

                sessionManager.createSession(token, sessionInfo);

                msgOut.setToken(token);
            }

            msgOut.setData(JSON.toJSONString(response));

            return msgOut;
        } catch (Exception e) {

            log.error("security failed.", e);

            return null;
        }
    }

    private Class<?> getAuthParameterType() {

        Type[] types = securityService.getClass().getGenericInterfaces();
        if(types == null || types.length == 0) {
            return Void.class;
        }

        for(Type type : types) {

            ParameterizedType ptype = (ParameterizedType)type;

            if(ptype.getRawType().getTypeName().equals(SecurityService.class.getTypeName())) {
                return (Class)ptype.getActualTypeArguments()[0];
            }
        }

        return null;
    }
}
