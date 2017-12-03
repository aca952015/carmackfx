package com.istudio.carmackfx.rpc.consumer.discovery.impl;

import com.istudio.carmackfx.model.consts.ConfigConsts;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.netflix.discovery.DefaultEurekaClientConfig;

import java.util.Arrays;
import java.util.List;

public class MyEurekaClientConfig extends DefaultEurekaClientConfig {

    private final ConfigProperties properties;

    public MyEurekaClientConfig(ConfigProperties properties) {

        super(ConfigConsts.REGISTER_CENTER_EUREKA_NAMESPACE);

        this.properties = properties;
    }

    @Override
    public String getRegion() {

        String region = super.getRegion();
        if(region == null) {

            return ConfigConsts.REGISTER_CENTER_EUREKA_REGION;
        }

        return region;
    }

    @Override
    public List<String> getEurekaServerServiceUrls(String myZone) {

        List<String> list = super.getEurekaServerServiceUrls(myZone);

        if(properties.getDiscoveryHost() != null) {

            list.addAll(Arrays.asList(properties.getDiscoveryHost().split(",")));
        }

        return list;
    }

    @Override
    public boolean shouldRegisterWithEureka() {

        return false;
    }
}
