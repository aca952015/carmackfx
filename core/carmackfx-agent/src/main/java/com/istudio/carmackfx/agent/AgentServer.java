package com.istudio.carmackfx.agent;

import com.alibaba.fastjson.JSON;
import com.istudio.carmackfx.protocol.MessageErrorContent;
import com.istudio.carmackfx.protocol.MessageIn;
import com.istudio.carmackfx.protocol.MessageOut;
import com.istudio.carmackfx.protocol.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.beykery.jkcp.KcpOnUdp;
import org.beykery.jkcp.KcpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ACA on 2017/6/7.
 */
@Slf4j
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

            if(msgIn.getType() == null) {
                throw new IllegalArgumentException("message type can not be null.");
            }

            if(msgIn.getType().equals(MessageType.AUTH) && StringUtils.isEmpty(msgIn.getData())) {
                throw new IllegalArgumentException("auth data can not be null.");
            }

            if(msgIn.getType().equals(MessageType.SERVER)) {

                if(msgIn.getToken() <= 0) {
                    throw new IllegalArgumentException("token can not be null.");
                }

                if(sessionManager.getSession(kcp) == null
                        || sessionManager.getSession(kcp).getLeft() != msgIn.getToken()) {

                    throw new IllegalArgumentException("token invalid.");
                }
            }

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

            send(kcp, msgOut);

        } catch(Exception e) {

            log.error("Process message failed.", e);

            MessageErrorContent errorContent = new MessageErrorContent();

            if(e instanceof IllegalArgumentException) {

                errorContent.setErrorCode(ErrorCodes.ILLEGAL_ARGUMENT.getCode());
                errorContent.setErrorMessage(ErrorCodes.ILLEGAL_ARGUMENT.getMessage() + ": " + e.getMessage());
            } if(e instanceof AgentException) {

                AgentException ex = (AgentException)e;

                errorContent.setErrorCode(ex.getErrorCode());
                errorContent.setErrorMessage(ex.getMessage());
            } else {

                errorContent.setErrorCode(ErrorCodes.SERVER_ERROR.getCode());
                errorContent.setErrorMessage(ErrorCodes.SERVER_ERROR.getMessage());
            }

            MessageOut msgOut = new MessageOut();
            msgOut.setId(-1);
            msgOut.setMode((byte)2);
            msgOut.setSuccess((byte)1);
            msgOut.setData(JSON.toJSONString(errorContent));

            send(kcp, msgOut);
        }
    }

    private void send(KcpOnUdp kcp, MessageOut msgOut) {

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
    }

    @Override
    public void handleException(Throwable ex, KcpOnUdp kcp) {

        log.error("handle exception.", ex);
    }

    @Override
    public void handleClose(KcpOnUdp kcp) {

        sessionManager.clearSession(kcp);
    }
}
