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

    public AgentServer(int port, int workerSize) {
        super(port, workerSize);
    }

    @Override
    public void handleReceive(ByteBuf bb, KcpOnUdp kcp) {

        int msgSize = bb.readInt();
        long msgId = bb.readLong();
        byte msgProt = bb.readByte();
        String content = bb.toString(Charset.forName("utf-8"));

        log.info("msg size: " + msgSize);
        log.info("msg id: " + msgId);
        log.info("msg protocol: " + msgProt);
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
