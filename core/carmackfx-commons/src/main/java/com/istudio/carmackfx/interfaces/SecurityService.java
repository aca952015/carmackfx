package com.istudio.carmackfx.interfaces;

import com.istudio.carmackfx.annotation.TService;
import com.istudio.carmackfx.domain.AuthResult;

/**
 * Created by ACA on 2017-6-7.
 */
@TService
public interface SecurityService<T> {

    AuthResult auth(T request);
}
