package com.istudio.carmackfx.rpc.provider.register;

import com.istudio.carmackfx.model.consts.ConfigConsts;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.provider.register.impl.EurekaServiceRegisterEntry;
import com.istudio.carmackfx.rpc.provider.register.impl.ZookeeperServiceRegisterEntry;

public class ServiceRegisterEntryFactory {

    public static ServiceRegisterEntry build(ConfigProperties properties) {

        if(ConfigConsts.REGISTER_CENTER_ZOOKEEPER.equalsIgnoreCase(properties.getDiscoveryType())) {

            return new ZookeeperServiceRegisterEntry(properties);
        } else if(ConfigConsts.REGISTER_CENTER_EUREKA.equalsIgnoreCase(properties.getDiscoveryType())) {

            return new EurekaServiceRegisterEntry(properties);
        }

        return null;
    }
}
