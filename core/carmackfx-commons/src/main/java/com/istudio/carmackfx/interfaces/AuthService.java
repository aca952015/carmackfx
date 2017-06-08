package com.istudio.carmackfx.interfaces;

import com.istudio.carmackfx.annotation.TService;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;

/**
 * Created by ACA on 2017/6/8.
 */
@TService()
public interface AuthService {

    MessageOut verify(MessageIn msgIn);
}
