package com.istudio.carmackfx.rpc.consumer.discovery.impl;

import com.istudio.carmackfx.model.consts.ConfigConsts;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.MyDataCenterInstanceConfig;

public class MyEurekarInstanceConfig extends MyDataCenterInstanceConfig implements EurekaInstanceConfig {

    private final ConfigProperties properties;

    public MyEurekarInstanceConfig(ConfigProperties properties) {

        super(ConfigConsts.REGISTER_CENTER_EUREKA_NAMESPACE);

        this.properties = properties;
    }
}
