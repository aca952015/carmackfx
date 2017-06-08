package com.istudio.carmackfx.rpc.consumer.core;

import com.istudio.carmackfx.config.BaseHolder;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.consumer.pool.impl.DirectSocketBuilder;
import com.istudio.carmackfx.rpc.consumer.pool.impl.DiscoverySocketBuilder;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ACA on 2017-5-22.
 */
@Log4j(topic = "thrift holder")
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
            builder.init();

            clientManager.setSocketBuilder(builder);
        } else {

            DirectSocketBuilder builder = new DirectSocketBuilder();
            builder.setProperties(properties);

            clientManager.setSocketBuilder(builder);
        }
    }
}
