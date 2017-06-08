package com.istudio.carmackfx.auth;

import com.istudio.carmackfx.interfaces.AuthProvider;
import com.istudio.carmackfx.interfaces.AuthService;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ACA on 2017/6/8.
 */
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthProvider authProvider;

    @Override
    public MessageOut verify(MessageIn msgIn) {



        return null;
    }
}
