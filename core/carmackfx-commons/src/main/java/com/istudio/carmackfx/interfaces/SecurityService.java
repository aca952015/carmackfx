package com.istudio.carmackfx.interfaces;

import com.istudio.carmackfx.annotation.TService;
import com.istudio.carmackfx.model.domain.User;

/**
 * Created by ACA on 2017-6-7.
 */
@TService
public interface SecurityService<T> {

    User auth(T request);
}
