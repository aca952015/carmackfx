package com.istudio.carmackfx.agent.processors;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.agent.MessageProcessor;
import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.protocol.MessageType;
import org.beykery.jkcp.KcpOnUdp;

/**
 * Created by ACA on 2017-6-13.
 */
@TProcessor(type = MessageType.SERVER)
public class ServerMessageProcessor implements MessageProcessor {

    @Override
    public MessageOut process(KcpOnUdp client, MessageIn msgIn) {

        JSON.parseObject()
    }
}
