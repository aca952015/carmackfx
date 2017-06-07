package com.istudio.carmackfx.agent;

import io.netty.buffer.ByteBuf;
import lombok.extern.log4j.Log4j;
import org.beykery.jkcp.KcpOnUdp;
import org.beykery.jkcp.KcpServer;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * Created by ACA on 2017/6/7.
 */
@Log4j
public class AgentServer extends KcpServer {

    public AgentServer(AgentProperties properties) {
        super(properties.getPort(), properties.getWorkerSize());
    }

    @Override
    public void handleReceive(ByteBuf bb, KcpOnUdp kcp) {

        int size = bb.readInt();
        long id = bb.readLong();
        byte prot = bb.readByte();
        String token = bb.readBytes(32).toString();
        String content = bb.toString(Charset.forName("utf-8"));

        log.info("msg size: " + size);
        log.info("msg id: " + id);
        log.info("msg protocol: " + prot);
        log.info("msg token: " + token);
        log.info("msg content: " + content);
//
//        MsgProcessor processor = Processors.getProcess(msgProt);
//        processo
    }

    @Override
    public void handleException(Throwable ex, KcpOnUdp kcp) {
    }

    @Override
    public void handleClose(KcpOnUdp kcp) {
    }
}
