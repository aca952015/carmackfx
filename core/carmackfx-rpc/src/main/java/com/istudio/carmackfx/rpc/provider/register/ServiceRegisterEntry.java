package com.istudio.carmackfx.rpc.provider.register;

import com.istudio.carmackfx.rpc.common.ServiceInfo;

/**
 * Created by aca on 2017/7/21.
 */
public interface ServiceRegisterEntry {

    void register(ServiceInfo info) throws Exception;
    void close();
}
