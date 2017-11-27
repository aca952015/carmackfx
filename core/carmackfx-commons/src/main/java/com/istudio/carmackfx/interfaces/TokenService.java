package com.istudio.carmackfx.interfaces;

import com.istudio.carmackfx.annotation.TService;
import com.istudio.carmackfx.domain.User;

@TService
public interface TokenService {

    long create(User user);
    boolean verify(long token);
    User get(long token);
}
