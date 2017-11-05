package com.istudio.carmackfx.rpc.provider.register.impl;

import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.common.ServiceInfo;
import com.istudio.carmackfx.rpc.provider.register.ServiceRegisterEntry;
import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EurekaServiceRegisterEntry implements ServiceRegisterEntry {

    private List<EurekaClient> clients;
    private ConfigProperties properties;

    public EurekaServiceRegisterEntry(ConfigProperties properties) {

        this.properties = properties;
        this.clients = new ArrayList<>();
    }

    @Override
    public void register(ServiceInfo info) throws Exception {

        EurekaInstanceConfig instanceConfig = new MyEurekarInstanceConfig(info, properties);
        InstanceInfo instanceInfo = new EurekaConfigBasedInstanceInfoProvider(instanceConfig).get();

        ApplicationInfoManager applicationInfoManager = new ApplicationInfoManager(instanceConfig, instanceInfo);
        EurekaClient eurekaClient = new DiscoveryClient(applicationInfoManager, new MyEurekaClientConfig(info, properties));

        applicationInfoManager.setInstanceStatus(InstanceInfo.InstanceStatus.UP);

        clients.add(eurekaClient);
    }

    @Override
    public void close() {

        for(EurekaClient eurekaClient : clients) {

            if(eurekaClient != null) {

                try {

                    eurekaClient.shutdown();
                } catch (Exception e) {

                    log.error("destroy eureka client failed", e);
                }
            }
        }
    }
}
