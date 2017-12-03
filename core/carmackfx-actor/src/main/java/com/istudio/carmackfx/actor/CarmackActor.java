package com.istudio.carmackfx.actor;

import akka.actor.UntypedAbstractActor;
import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
public class CarmackActor extends UntypedAbstractActor {

    private static HashMap<String, Method> cache = new HashMap<>();

    @Override
    public void onReceive(Object message) throws Throwable {

        if(message instanceof ActorMessage) {

            ActorMessage actorMessage = (ActorMessage)message;

            Method method = findMethod(actorMessage);
            if(method != null && method.getParameterCount() == actorMessage.getArguments().size()) {

                Object[] inArgs = new Object[method.getParameterCount()];
                Class[] ptypes = method.getParameterTypes();
                List<Object> vals = actorMessage.getArguments();

                for(int pos =0; pos < ptypes.length; pos++) {

                    Class ptype = ptypes[pos];
                    Object val = vals.get(pos);

                    if(val == null || val.getClass().equals(ptype)) {
                        inArgs[pos] = val;
                    } else if(val instanceof String) {
                        inArgs[pos] = JSON.parseObject((String)val, ptype);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }

                method.invoke(this, inArgs);
            } else {
                unhandled(message);
            }
        } else {
            unhandled(message);
        }
    }

    private Method findMethod(ActorMessage actorMessage) {

        Class clazz = this.getClass();
        String name = actorMessage.getName();
        String key = clazz.getName() + "." + actorMessage.getName().toLowerCase();
        int arguCount = actorMessage.getArguments().size();

        if(cache.containsKey(key)) {

            return cache.get(key);
        }

        Method[] methods = this.getClass().getMethods();
        for(Method method : methods) {
            if(method.getName().equalsIgnoreCase(name)
                    && method.getParameterCount() == arguCount) {

                cache.put(key, method);

                return method;
            }
        }

        return null;
    }
}
