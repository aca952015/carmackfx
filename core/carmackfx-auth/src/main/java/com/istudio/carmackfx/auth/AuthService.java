package com.istudio.carmackfx.auth;

import com.istudio.carmackfx.commons.protocol.MessageIn;
import com.istudio.carmackfx.commons.protocol.MessageOut;
import com.istudio.carmackfx.rpc.annotation.TService;

/**
 * Created by ACA on 2017-6-7.
 */
@TService
public interface AuthService {

    MessageOut verify(MessageIn msg);
}
