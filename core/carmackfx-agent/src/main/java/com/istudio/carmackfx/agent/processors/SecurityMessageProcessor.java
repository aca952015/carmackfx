package com.istudio.carmackfx.agent.processors;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.agent.MessageProcessor;
import com.istudio.carmackfx.agent.SessionInfo;
import com.istudio.carmackfx.agent.SessionManager;
import com.istudio.carmackfx.agent.impls.DefaultSecurityService;
import com.istudio.carmackfx.agent.impls.DefaultTokenService;
import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.domain.AuthResult;
import com.istudio.carmackfx.domain.User;
import com.istudio.carmackfx.interfaces.SecurityService;
import com.istudio.carmackfx.interfaces.TokenService;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.protocol.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.beykery.jkcp.KcpOnUdp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

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

    @Override
    public MessageOut process(KcpOnUdp client, MessageIn msgIn) {

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

        try {

            Object argu = null;

            if(!authParameterType.getName().equals("System.lang.Void")) {
                argu = JSON.parseObject(msgIn.getData(), authParameterType);
            }

            AuthResult result = securityService.auth(argu);

            MessageOut msgOut = new MessageOut();
            msgOut.setId(msgIn.getId());
            msgOut.setSuccess((byte) (result.isSuccess() ? 0 : 1));
            msgOut.setData(JSON.toJSONString(result));

            if(result.getUser() == null
                    || StringUtils.isEmpty(result.getUser().getUsername())) {
                result.setSuccess(false);
            }

            if(result.isSuccess()) {

                User user = result.getUser();

                long token = tokenService.create(result.getUser());

                SessionInfo sessionInfo = new SessionInfo(
                        user.getUsername(),
                        token,
                        user.getUsername(),
                        user.getNickname(),
                        client
                );

                sessionManager.createSession(user.getUsername(), sessionInfo);

                msgOut.setToken(token);
            }

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
