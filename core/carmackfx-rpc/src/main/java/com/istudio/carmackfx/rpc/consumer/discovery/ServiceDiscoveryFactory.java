package com.istudio.carmackfx.rpc.consumer.discovery;

import com.istudio.carmackfx.model.consts.ConfigConsts;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.consumer.discovery.impl.EurekaServiceDiscovery;
import com.istudio.carmackfx.rpc.consumer.discovery.impl.ZookeeperServiceDiscovery;

public class ServiceDiscoveryFactory {

    public static ServiceDiscovery build(ConfigProperties properties) {

        if(ConfigConsts.REGISTER_CENTER_ZOOKEEPER.equalsIgnoreCase(properties.getDiscoveryType())) {
            return new ZookeeperServiceDiscovery(properties);
        } else if(ConfigConsts.REGISTER_CENTER_EUREKA.equalsIgnoreCase(properties.getDiscoveryType())) {
            return new EurekaServiceDiscovery(properties);
        }

        return null;
    }
}
