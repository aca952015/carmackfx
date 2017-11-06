package com.istudio.carmackfx.agent.processors;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.agent.AgentHolder;
import com.istudio.carmackfx.agent.MessageProcessor;
import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.protocol.MessageType;
import lombok.Getter;
import lombok.Setter;
import org.beykery.jkcp.KcpOnUdp;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ACA on 2017-6-13.
 */
@TProcessor(type = MessageType.SERVER)
public class ServerMessageProcessor implements MessageProcessor {

    @Autowired
    private AgentHolder holder;

    @Override
    public MessageOut process(KcpOnUdp client, MessageIn msgIn) {

        if(msgIn.getData() == null) {
            throw new IllegalArgumentException("msg data can not be null.");
        }

        RpcMessageData data = JSON.parseObject(msgIn.getData(), RpcMessageData.class);
        if(data == null) {
            throw new IllegalArgumentException("msg data invalid.");
        }

        MessageOut msgOut = new MessageOut();
        msgOut.setId(msgIn.getId());

        Object serviceInstance = holder.getBean(data.getServiceName());
        if(serviceInstance == null) {

        }

        return null;
    }

    @Getter
    @Setter
    public static class RpcMessageData {

        private String serviceName;
        private String methodName;
        private String[] arguments;
    }
}
