package com.istudio.carmackfx.rpc.consumer.pool.impl;

import com.istudio.carmackfx.rpc.common.ConfigProperties;
import com.istudio.carmackfx.rpc.consumer.core.SocketBuilder;
import com.istudio.carmackfx.rpc.consumer.core.SocketFactory;
import lombok.Getter;
import lombok.Setter;
import org.apache.thrift.transport.TSocket;

/**
 * Created by ACA on 2017-5-28.
 */
@Getter
@Setter
public class DirectSocketBuilder implements SocketBuilder {

    private ConfigProperties properties;

    @Override
    public TSocket newSocket(Class iface) {

        if(iface == null) {
            return null;
        }

        return SocketFactory.newTSocket(
                properties.getHost(),
                properties.getPort(),
                properties.getConnectionConnectTimeout(),
                properties.getConnectionSocketTimeout(),
                properties.getConnectionTimeout());
    }
}
