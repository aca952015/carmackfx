package com.istudio.carmackfx.rpc.consumer.core;

import com.istudio.carmackfx.config.BaseHolder;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.consumer.discovery.ServiceDiscoveryFactory;
import com.istudio.carmackfx.rpc.consumer.pool.impl.DirectSocketBuilder;
import com.istudio.carmackfx.rpc.consumer.pool.impl.DiscoverySocketBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ACA on 2017-5-22.
 */
@Slf4j(topic = "thrift holder")
public class ClientHolder extends BaseHolder {

    @Autowired
    private ConfigProperties properties;

    @Autowired
    private ServiceClientManager clientManager;

    @Override
    protected void doInit() {

        if(properties.isDiscoveryEnabled()) {

            DiscoverySocketBuilder builder = new DiscoverySocketBuilder();
            builder.setProperties(properties);
            builder.setServiceDiscovery(ServiceDiscoveryFactory.build(properties));

            clientManager.setSocketBuilder(builder);
        } else {

            DirectSocketBuilder builder = new DirectSocketBuilder();
            builder.setProperties(properties);

            clientManager.setSocketBuilder(builder);
        }
    }
}
