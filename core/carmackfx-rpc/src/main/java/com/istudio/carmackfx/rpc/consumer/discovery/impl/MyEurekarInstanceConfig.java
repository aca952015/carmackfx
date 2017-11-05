package com.istudio.carmackfx.rpc.consumer.discovery.impl;

import com.istudio.carmackfx.config.Consts;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.MyDataCenterInstanceConfig;

public class MyEurekarInstanceConfig extends MyDataCenterInstanceConfig implements EurekaInstanceConfig {

    private final ConfigProperties properties;

    public MyEurekarInstanceConfig(ConfigProperties properties) {

        super(Consts.REGISTER_CENTER_EUREKA_NAMESPACE);

        this.properties = properties;
    }
}
