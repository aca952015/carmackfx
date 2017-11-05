package com.istudio.carmackfx.rpc.consumer.pool.impl;

import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.common.ServiceInfo;
import com.istudio.carmackfx.rpc.consumer.core.SocketBuilder;
import com.istudio.carmackfx.rpc.consumer.core.SocketFactory;
import com.istudio.carmackfx.rpc.consumer.discovery.ServiceDiscovery;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.transport.TSocket;

/**
 * Created by ACA on 2017-5-28.
 */
@Getter
@Setter
@Slf4j
public class DiscoverySocketBuilder implements SocketBuilder {

    private ConfigProperties properties;
    private ServiceDiscovery serviceDiscovery;

    @Override
    public TSocket newSocket(Class iface) {

        try {

            ServiceInfo serviceInfo = serviceDiscovery.getServiceInfo(iface);

            return SocketFactory.newTSocket(
                    serviceInfo.getHost(),
                    serviceInfo.getPort(),
                    properties.getConnectionConnectTimeout(),
                    properties.getConnectionSocketTimeout(),
                    properties.getConnectionTimeout());
        } catch (Exception e) {

            log.error("get instance failed: " + iface.getName());

            return null;
        }
    }

    public DiscoverySocketBuilder() {
    }
}
