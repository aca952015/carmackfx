package com.istudio.carmackfx.rpc.consumer.discovery;

import com.istudio.carmackfx.rpc.common.ServiceInfo;

public interface ServiceDiscovery {

    ServiceInfo getServiceInfo(Class iface) throws Exception;
}
