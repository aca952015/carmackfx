package com.istudio.carmackfx.agent.processors;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.agent.*;
import com.istudio.carmackfx.agent.impls.DefaultTokenService;
import com.istudio.carmackfx.annotation.TContext;
import com.istudio.carmackfx.annotation.TParam;
import com.istudio.carmackfx.annotation.TPublic;
import com.istudio.carmackfx.exceptions.MessageException;
import com.istudio.carmackfx.interfaces.TokenService;
import com.istudio.carmackfx.agent.SessionInfo;
import com.istudio.carmackfx.model.consts.ErrorCodes;
import com.istudio.carmackfx.model.request.RpcMessageArgument;
import com.istudio.carmackfx.model.request.RpcMessageData;
import com.istudio.carmackfx.protocol.*;
import com.istudio.carmackfx.utils.AnnotationUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.beykery.jkcp.KcpOnUdp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class ServiceMessageProcessor implements MessageProcessor {

    @Autowired
    protected AgentHolder holder;

    @Autowired
    protected SessionManager sessionManager;

    @Autowired(required = false)
    protected TokenService tokenService;

    private static final Map<Method, ArgumentMap> argumentsMap = new HashMap<>();

    @Override
    public void init() {

        if(tokenService == null) {

            tokenService = new DefaultTokenService();
        }
    }

    protected MessageOut process(KcpOnUdp client, MessageIn msgIn, boolean isPublic) throws Exception {

        if(msgIn.getData() == null) {
            throw new IllegalArgumentException("msg data can not be null.");
        }

        RpcMessageData data = JSON.parseObject(msgIn.getData(), RpcMessageData.class);
        if(data == null) {
            throw new IllegalArgumentException("msg data invalid.");
        }

        MessageOut msgOut = new MessageOut();
        msgOut.setId(msgIn.getId());

        Object serviceInstance = holder.getBean(data.getServiceName());
        if(serviceInstance == null) {
            throw new AgentException(ErrorCodes.SERVICE_NOT_FOUND);
        }

        if(isPublic == true) {

            TPublic anno = serviceInstance.getClass().getAnnotation(TPublic.class);
            if(anno == null) {
                throw new AgentException(ErrorCodes.SERVICE_NOT_PUBLIC);
            }
        } else if(tokenService.verify(msgIn.getToken()) == false) {
            throw new AgentException(ErrorCodes.TOKEN_INVALID);
        }

        Method method = AnnotationUtils.getMethod(serviceInstance.getClass(), data.getMethodName());
        if(method == null) {
            throw new AgentException(ErrorCodes.METHOD_NOT_FOUND);
        }

        Object[] args = buildArgs(method, sessionManager.getSession(client), data.getArguments());

        try {
            Object result = method.invoke(serviceInstance, args);

            msgOut.setId(msgIn.getId());
            msgOut.setSuccess(MessageConsts.MSG_SUCCESS);
            msgOut.setMode(MessageConsts.MSG_RESULT);
            msgOut.setData(JSON.toJSONString(result));

            log.info("service invoked: {}.{}", data.getServiceName(), data.getMethodName());

        } catch (InvocationTargetException e) {

            if(e.getTargetException() instanceof MessageException) {

                MessageErrorContent content = new MessageErrorContent();
                content.setErrorCode(ErrorCodes.BUSINESS_ERROR.getCode());
                content.setErrorMessage(e.getTargetException().getMessage());

                msgOut.setId(msgIn.getId());
                msgOut.setSuccess(MessageConsts.MSG_DATAINVALID);
                msgOut.setMode(MessageConsts.MSG_RESULT);
                msgOut.setData(JSON.toJSONString(content));

                return msgOut;
            }

            log.error("failed to invoke service method: {}.{}", data.getServiceName(), data.getMethodName(), e);

            throw new AgentException(ErrorCodes.SERVER_ERROR);
        }

        return msgOut;
    }

    private Object[] buildArgs(Method method, SessionInfo sessionInfo, Map<String, RpcMessageArgument> arguments) {

        // init argument map cache
        ArgumentMap map;
        if(argumentsMap.containsKey(method)) {
            map = argumentsMap.get(method);
        } else {

            Type[] ptypes = method.getParameterTypes();
            Annotation[][] panns = method.getParameterAnnotations();

            Map<Integer, String> argumentsIndex = new HashMap<>();
            Class[] methodArguments = new Class[ptypes.length];

            map = new ArgumentMap();

            for (int pos = 0; pos < ptypes.length; pos++) {

                Class ptype = (Class) ptypes[pos];
                if(panns[pos].length > 0) {
                    for(Annotation anno : panns[pos]) {

                        if(anno instanceof TContext) {
                            map.setContextIndex(pos);
                        } else if(anno instanceof TParam) {
                            argumentsIndex.put(pos, ((TParam)anno).value());
                        }
                    }
                }

                methodArguments[pos] = ptype;
            }

            map.setArgumentsIndex(argumentsIndex);
            map.setMethodArguments(methodArguments);

            argumentsMap.put(method, map);
        }

        Object[] args = new Object[map.methodArguments.length];

        if(arguments != null && arguments.size() > 0) {

            for (int index : map.getArgumentsIndex().keySet()) {

                String pramName = map.getArgumentsIndex().get(index);
                if (arguments.containsKey(pramName)) {

                    RpcMessageArgument argument = arguments.get(pramName);

                    String value = argument.getArgumentValue();

                    if(!StringUtils.isEmpty(value)) {

                        args[index] = JSON.parseObject(value, map.getMethodArguments()[index]);
                    }
                }
            }
        }

        if(map.getContextIndex() != null && map.getContextIndex() < map.getMethodArguments().length) {

            MessageContext context = new MessageContext();

            if(sessionInfo != null) {
                context.setSessionId(sessionInfo.getSessionId());
                context.setToken(sessionInfo.getToken());
                context.setId(sessionInfo.getId());
                context.setNickname(sessionInfo.getNickname());
            }

            args[map.getContextIndex()] = context;
        }

        return args;
    }

    @Data
    private static class ArgumentMap {

        private Map<Integer, String> argumentsIndex;
        private Class<?>[] methodArguments;
        private Integer contextIndex;
    }
}
