package com.istudio.carmackfx.auth.impl;

import com.istudio.carmackfx.auth.AuthService;
import com.istudio.carmackfx.commons.protocol.MessageIn;
import com.istudio.carmackfx.commons.protocol.MessageOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.AuthProvider;

/**
 * Created by ACA on 2017-6-7.
 */
@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthProvider provider;

    @Override
    public MessageOut verify(MessageIn msg) {
        return null;
    }
}
