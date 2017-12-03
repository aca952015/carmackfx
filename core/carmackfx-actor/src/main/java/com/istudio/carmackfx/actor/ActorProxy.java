package com.istudio.carmackfx.actor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@AllArgsConstructor
public class ActorProxy implements InvocationHandler {

    private ActorInstance instance;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        instance.tell(method.getName(), Arrays.asList(args));

        return null;
    }
}
