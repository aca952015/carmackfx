package com.istudio.carmackfx.agent.processors;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.agent.*;
import com.istudio.carmackfx.agent.impls.DefaultTokenService;
import com.istudio.carmackfx.annotation.TContext;
import com.istudio.carmackfx.annotation.TParam;
import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.annotation.TPublic;
import com.istudio.carmackfx.interfaces.TokenService;
import com.istudio.carmackfx.protocol.*;
import com.istudio.carmackfx.utils.AnnotationUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.beykery.jkcp.KcpOnUdp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ACA on 2017-6-13.
 */
@TProcessor(type = MessageType.PUBLIC)
@Slf4j
public class PublicMessageProcessor extends ServiceMessageProcessor {

    @Override
    public MessageOut process(KcpOnUdp client, MessageIn msgIn) throws Exception {

        return process(client, msgIn, true);
    }
}
