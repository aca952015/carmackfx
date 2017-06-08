package com.istudio.carmackfx.interfaces;

/**
 * Created by ACA on 2017-6-7.
 */
public interface AuthProvider<TIn, TOut> {

    TOut verify(TIn authIn);
}
