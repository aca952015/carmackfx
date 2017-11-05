package com.istudio.carmackfx.rpc.provider.core;

import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.provider.ServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ACA on 2017-5-28.
 */
@Slf4j
public class ServerContainer {

    private ServerConfig config;

    private TServer server;

    @Autowired
    private ConfigProperties properties;

    public ServerContainer(ServerConfig config) {
        this.config = config;
    }

    public void serve(TProcessor processor) {

        Thread thread = new Thread(() -> {

            try {

                // 传输通道 - 非阻塞方式
                TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(this.properties.getPort());

                //异步IO，需要使用TFramedTransport，它将分块缓存读取。
                TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);

                tArgs.transportFactory(new TFramedTransport.Factory());

                //使用高密度二进制协议
                tArgs.protocolFactory(new TCompactProtocol.Factory());

                log.info("start server at port: " + properties.getPort());

                tArgs.processor(processor);

                // 注册服务到注册中心
                if (properties.isDiscoveryEnabled()) {

                    //discoveryClient.
                }

                // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
                server = new TNonblockingServer(tArgs);
                server.serve(); // 启动服务
            } catch (Exception ex) {
                log.error("failed to start", ex);
            }
        });

        thread.start();
    }

    public void stop() {
        try {
            if (server != null && server.isServing()) {
                server.stop();
            }
        } catch (Exception e) {
            log.error("failed to stop.", e);
        }
    }
}
