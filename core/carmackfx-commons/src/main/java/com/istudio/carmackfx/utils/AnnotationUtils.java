package com.istudio.carmackfx.utils;

import com.istudio.carmackfx.annotation.TCallback;
import com.istudio.carmackfx.annotation.TMethod;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by ACA on 2017-6-10.
 */
public class AnnotationUtils {

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

    public static String getMethodName(Method method) {

        TMethod anno = method.getAnnotation(TMethod.class);
        if(anno != null && !StringUtils.isEmpty(anno.value())) {
            return anno.value();
        }

        return method.getName();
    }

    public static String getCallbackName(Class clazz) {

        TCallback anno = (TCallback)clazz.getAnnotation(TCallback.class);
        if(anno != null && !StringUtils.isEmpty(anno.value())) {
            return anno.value();
        }

        return clazz.getSimpleName();
    }
}
