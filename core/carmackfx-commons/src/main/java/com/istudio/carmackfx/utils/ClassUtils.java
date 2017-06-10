package com.istudio.carmackfx.utils;

import java.lang.reflect.Method;

/**
 * Created by ACA on 2017-6-10.
 */
public class ClassUtils {

    public static Method getMethod(Class clazz, String methodName) {

        for(Method method : clazz.getMethods()) {

            if(method.getName().equalsIgnoreCase(methodName)) {

                return method;
            }
        }

        return null;
    }
}
