package com.istudio.carmackfx.rpc.provider.core;

import com.istudio.carmackfx.config.BaseHolder;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.common.ServiceDefinition;
import com.istudio.carmackfx.rpc.provider.ServerConfig;
import com.istudio.carmackfx.rpc.provider.register.ServiceRegister;
import com.istudio.carmackfx.rpc.provider.register.ServiceRegisterEntry;
import com.istudio.carmackfx.rpc.provider.register.ServiceRegisterEntryFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ACA on 2017/5/22.
 */
@Slf4j(topic = "thrift server holder")
public class ServerHolder extends BaseHolder {

    private ServerConfig config;

    @Autowired
    private ServerContainer container;

    @Autowired
    private ServiceRegister register;

    @Autowired
    private ConfigProperties properties;

    public ServerHolder(ServerConfig config) {
        this.config = config;
    }

    @Override
    protected void doInit() {

        try {
            startServer();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    private void startServer() throws TTransportException {

        log.info("starting server");

        Class[] services = this.config.getServices();
        if (services != null || services.length > 0) {

            TMultiplexedProcessor multiProcessor = new TMultiplexedProcessor();

            for (Class service : services) {

                // 处理器关联业务实现
                if (service.getInterfaces().length == 0) {
                    continue;
                }

                Class iface = service.getInterfaces()[0];

                // 反射类型
                ServiceDefinition.register(iface);

                String serviceName = iface.getName();

                Object target = getBean(service);

                ServerProcessor processorObj = new ServerProcessor(iface, target);
                multiProcessor.registerProcessor(serviceName, processorObj);

                log.info("service loaded: " + serviceName);
            }

            container.serve(multiProcessor);

            if(properties.isDiscoveryEnabled()) {

                ServiceRegisterEntry entry = ServiceRegisterEntryFactory.build(properties);
                if(entry != null) {
                    register.setEntry(entry);
                    register.init();
                }
            }
        }
    }
}