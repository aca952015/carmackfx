package com.istudio.carmackfx.annotation;

import com.istudio.carmackfx.protocol.MessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ACA on 2017/6/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TProcessor {
    MessageType type() default MessageType.GAME;
}
