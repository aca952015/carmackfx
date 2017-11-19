package com.istudio.carmackfx.agent.impls;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.callback.CallbackData;
import com.istudio.carmackfx.callback.CallbackService;
import com.istudio.carmackfx.callback.ClientManager;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.utils.AnnotationUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ACA on 2017-6-11.
 */
public class DefaultClientManager implements ClientManager {

    @Autowired
    private CallbackService callbackService;

    @Override
    public <T> T getCallback(String sessionId, Class<T> clazz) {

        ClientProxy proxy = new ClientProxy(callbackService, sessionId, clazz);

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, proxy);
    }

    @AllArgsConstructor
    private class ClientProxy implements InvocationHandler {

        private CallbackService callbackService;
        private String sessionId;
        private Class serviceType;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            CallbackData data = new CallbackData();
            data.setServiceName(AnnotationUtils.getCallbackName(serviceType));
            data.setMethodName(AnnotationUtils.getMethodName(method));
            data.setArgs(argsToList(args));

            MessageOut msgOut = new MessageOut();
            msgOut.setData(JSON.toJSONString(data));

            callbackService.send(sessionId, msgOut);

            return null;
        }

        private String[] argsToList(Object[] args) {

            if(args == null || args.length == 0) {
                return null;
            }

            String[] outArgs = new String[args.length];

            for(int pos = 0; pos < args.length; pos++) {

                if(args[pos] != null) {
                    outArgs[pos] = args[pos].toString();
                }
            }

            return outArgs;
        }
    }
}
