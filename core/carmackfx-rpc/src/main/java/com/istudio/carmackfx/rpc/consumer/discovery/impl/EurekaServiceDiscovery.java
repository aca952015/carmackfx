package com.istudio.carmackfx.rpc.consumer.discovery.impl;

import com.istudio.carmackfx.model.consts.ConfigConsts;
import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.common.ServiceInfo;
import com.istudio.carmackfx.rpc.consumer.discovery.ServiceDiscovery;
import com.istudio.carmackfx.rpc.utils.ServiceUtils;
import com.istudio.carmackfx.utils.EurekaUtils;
import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class EurekaServiceDiscovery implements ServiceDiscovery {

    private ConfigProperties properties;
    private ApplicationInfoManager applicationInfoManager;
    private EurekaClient eurekaClient;

    public EurekaServiceDiscovery(ConfigProperties properties) {

        this.properties = properties;

        init();
    }

    private void init() {

        EurekaInstanceConfig instanceConfig = new MyEurekarInstanceConfig(properties);
        InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();

        applicationInfoManager = new ApplicationInfoManager(instanceConfig, instanceInfo);
        eurekaClient = new DiscoveryClient(applicationInfoManager, new MyEurekaClientConfig(properties));
    }

    @Override
    public ServiceInfo getServiceInfo(Class iface) throws Exception {

        String path = EurekaUtils.getPath(ServiceUtils.getName(iface), ServiceUtils.getVersion(iface));

        try {

            InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(path, false);

            String ipAddress = instanceInfo.getIPAddr();
            String[] pairs = ipAddress.split(":");

            ServiceInfo serviceInfo = new ServiceInfo();
            serviceInfo.setId(UUID.randomUUID().toString());
            serviceInfo.setGroup(instanceInfo.getAppGroupName());
            serviceInfo.setHost(pairs[0]);
            serviceInfo.setPort(pairs.length == 2 ? Integer.valueOf(pairs[1]) : ConfigConsts.SERVER_DEFAULT_PORT);
            serviceInfo.setVersion(ServiceUtils.getVersion(iface));

            return serviceInfo;

        } catch (Exception e) {
            log.error("Cannot get an instance of example service to talk to from eureka", e);

            return null;
        }
    }
}
