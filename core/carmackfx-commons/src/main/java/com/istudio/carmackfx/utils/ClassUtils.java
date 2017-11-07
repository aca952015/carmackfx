package com.istudio.carmackfx.utils;

import com.istudio.carmackfx.annotation.TMethod;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by ACA on 2017-6-10.
 */
public class ClassUtils {

    private static HashMap<String, Method> caches = new HashMap<>();

    public static Method getMethod(Class clazz, String methodName) {

        String key = clazz.getName() + "." + methodName;
        if(caches.containsKey(key)) {
            return caches.get(key);
        }

        for(Method method : clazz.getMethods()) {

            TMethod anno = method.getAnnotation(TMethod.class);
            if(anno == null) {
                continue;
            }

            if(anno.value().equalsIgnoreCase(methodName)) {

                caches.put(key, method);

                return method;
            }
        }

        return null;
    }
}
