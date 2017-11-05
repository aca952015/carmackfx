package com.istudio.carmackfx.rpc.provider.register.impl;

import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.common.ServiceInfo;
import com.istudio.carmackfx.rpc.provider.register.ServiceRegisterEntry;
import mousio.etcd4j.EtcdClient;

public class EtcdServiceRegisterEntry implements ServiceRegisterEntry {

    private EtcdClient client;
    private ConfigProperties properties;

    public EtcdServiceRegisterEntry(ConfigProperties properties) {

        this.properties = properties;
    }

    @Override
    public void register(ServiceInfo info) throws Exception {

    }

    @Override
    public void close() {

    }
}
