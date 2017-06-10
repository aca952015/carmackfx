package com.istudio.carmackfx.auth;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.interfaces.AuthProvider;
import com.istudio.carmackfx.interfaces.AuthResult;
import com.istudio.carmackfx.interfaces.AuthService;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.utils.ClassUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

/**
 * Created by ACA on 2017/6/8.
 */
@Log4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthProvider authProvider;

    private Class<?> authParameterType;

    @Override
    public MessageOut verify(MessageIn msgIn) {

        if(authProvider == null) {

            return null;
        }

        if(authParameterType == null) {

            Method method = ClassUtils.getMethod(authProvider.getClass(), "verify");
            if(method != null) {

                authParameterType = method.getParameterTypes()[0];
            }
        }

        try {
            AuthResult result = authProvider.verify(JSON.parseObject(msgIn.getData(), authParameterType));

            MessageOut msgOut = new MessageOut();
            msgOut.setId(msgIn.getId());
            msgOut.setSuccess((byte)((result.getToken() != 0L) ? 0 : 1));
            msgOut.setData(JSON.toJSONString(result));

            return msgOut;
        } catch (Exception e) {

            log.error("Auth failed.", e);

            return null;
        }
    }
}