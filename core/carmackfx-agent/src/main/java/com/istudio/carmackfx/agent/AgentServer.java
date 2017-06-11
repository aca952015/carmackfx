package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.protocol.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.extern.log4j.Log4j;
import org.beykery.jkcp.KcpOnUdp;
import org.beykery.jkcp.KcpServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ACA on 2017/6/7.
 */
@Log4j
public class AgentServer extends KcpServer {

    private static final Charset charset = Charset.forName("utf-8");
    private static final int offset = 14;

    @Autowired
    private MessageProcessorManager processorManager;

    @Autowired
    private SessionManager sessionManager;

    public AgentServer(AgentProperties properties) {
        super(properties.getPort(), properties.getWorkerSize());
    }

    @Override
    public void handleReceive(ByteBuf bufferIn, KcpOnUdp kcp) {

        try {

            MessageIn msgIn = new MessageIn();
            msgIn.setSize(bufferIn.readInt());
            msgIn.setId(bufferIn.readLong());
            msgIn.setType(MessageType.valueOf(bufferIn.readByte()));
            msgIn.setToken(bufferIn.readLong());
            msgIn.setData(bufferIn.toString(charset));

            MessageOut msgOut = null;

            MessageProcessor processor = processorManager.getProcessor(msgIn.getType());
            if (processor!= null) {

                msgOut = processor.process(kcp, msgIn);
                msgOut.setId(msgIn.getId());
                msgOut.setMode((byte)0);
            }

            if(msgOut == null) {

                msgOut = new MessageOut();
                msgOut.setId(msgIn.getId());
                msgOut.setMode((byte)0);
                msgOut.setSuccess((byte)1);
            }

            byte[] data = msgOut.getData() == null ? null : msgOut.getData().getBytes(charset);

            ByteBuf bufferOut = PooledByteBufAllocator.DEFAULT.buffer(1500);
            bufferOut.writeInt(data == null ? 0 : data.length + offset);
            bufferOut.writeLong(msgOut.getId());
            bufferOut.writeByte(msgOut.getMode());
            bufferOut.writeByte(msgOut.getSuccess());

            if(data != null) {
                bufferOut.writeBytes(data);
            }

            kcp.send(bufferOut);

        } catch(Exception e) {

            log.error("Process message failed.", e);
        }
    }

    @Override
    public void handleException(Throwable ex, KcpOnUdp kcp) {
    }

    @Override
    public void handleClose(KcpOnUdp kcp) {

        sessionManager.clearSession(kcp);
    }
}
