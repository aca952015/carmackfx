package com.istudio.carmackfx.rpc.provider;

import com.istudio.carmackfx.rpc.common.BaseConfig;
import com.istudio.carmackfx.rpc.provider.core.ServerContainer;
import com.istudio.carmackfx.rpc.provider.core.ServerHolder;
import com.istudio.carmackfx.rpc.provider.register.ServiceRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * Created by ACA on 2017/5/22.
 */
@Slf4j(topic = "thrift server config")
public class ServerConfig extends BaseConfig {

    @Bean
    public ServerHolder serverHolder() {
        return new ServerHolder(this);
    }

    @Bean
    public ServerContainer serverContainer() {
        return new ServerContainer(this);
    }

    @Bean
    public ServiceRegister serverRegister() {
        return new ServiceRegister(this);
    }
}
