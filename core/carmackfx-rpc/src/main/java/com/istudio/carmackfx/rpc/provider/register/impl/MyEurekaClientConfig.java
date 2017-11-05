package com.istudio.carmackfx.rpc.provider.register.impl;

import com.istudio.carmackfx.config.Consts;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.common.ServiceInfo;
import com.netflix.discovery.DefaultEurekaClientConfig;

import java.util.Arrays;
import java.util.List;

public class MyEurekaClientConfig extends DefaultEurekaClientConfig {

    private final ServiceInfo info;
    private final ConfigProperties properties;

    public MyEurekaClientConfig(ServiceInfo info, ConfigProperties properties) {

        super(Consts.REGISTER_CENTER_EUREKA_NAMESPACE);

        this.info = info;
        this.properties = properties;
    }

    @Override
    public String getRegion() {

        String region = super.getRegion();
        if(region == null) {

            return Consts.REGISTER_CENTER_EUREKA_REGION;
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
}
