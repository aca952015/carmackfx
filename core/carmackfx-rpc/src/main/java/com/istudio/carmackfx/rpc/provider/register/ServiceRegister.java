package com.istudio.carmackfx.rpc.provider.register;

import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.common.ServiceDefinition;
import com.istudio.carmackfx.rpc.common.ServiceInfo;
import com.istudio.carmackfx.rpc.provider.ServerConfig;
import com.istudio.carmackfx.rpc.utils.ServiceUtils;
import com.istudio.carmackfx.utils.NetworkUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * Created by ACA on 2017-5-28.
 */
@Slf4j
public class ServiceRegister implements DisposableBean {

    private ServerConfig config;

    @Autowired
    private ConfigProperties properties;

    @Setter
    private ServiceRegisterEntry entry;

    public ServiceRegister(ServerConfig config) {
        this.config = config;
    }

    public void init() {

        try {

            log.info("registering services");


            for (ServiceDefinition def : ServiceDefinition.getDefs()) {

                Class iface = def.getIface();
                if (!ServiceUtils.isTService(iface)) {
                    continue;
                }

                String name = ServiceUtils.getName(iface);
                String group = ServiceUtils.getGroup(iface);
                String version = ServiceUtils.getVersion(iface);

                try {

                    ServiceInfo info = new ServiceInfo(UUID.randomUUID().toString(), name, group, version, StringUtils.isEmpty(properties.getHost()) ? NetworkUtils.getLocalAddress() : properties.getHost(), properties.getPort());
                    entry.register(info);
                } catch(Exception e) {

                    log.error("register service failed: " + name, e);
                }
            }
        } catch(Exception e) {
            log.error("register service failed", e);

            close();
        }
    }

    public void close() {

        if(entry != null) {

            entry.close();
        }
    }

    @Override
    public void destroy() throws Exception {

        this.close();
    }
}
