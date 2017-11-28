package com.istudio.carmackfx.agent.processors;

import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.protocol.MessageType;
import org.beykery.jkcp.KcpOnUdp;

@TProcessor(type = MessageType.INTERNAL)
public class InternalMessageProcessor extends ServiceMessageProcessor {

    @Override
    public MessageOut process(KcpOnUdp client, MessageIn msgIn) throws Exception {

        return super.process(client, msgIn, false);
    }
}
