package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import org.beykery.jkcp.KcpOnUdp;

/**
 * Created by ACA on 2017-6-7.
 */
public interface MessageProcessor {

    MessageOut process(KcpOnUdp client, MessageIn msgIn);
}
