package com.istudio.carmackfx.annotation;

import com.istudio.carmackfx.config.Consts;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ACA on 2017/4/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TService {
    String group() default Consts.SERVICE_BASE_GROUP;
    String version() default Consts.SERVICE_BASE_VERSION;
}
