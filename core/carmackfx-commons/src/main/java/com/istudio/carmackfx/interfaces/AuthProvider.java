package com.istudio.carmackfx.interfaces;

/**
 * Created by ACA on 2017-6-7.
 */
public interface AuthProvider<T> {

    AuthResult verify(T authIn);
}
