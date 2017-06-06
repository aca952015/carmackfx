package com.istudio.carmackfx.rpc.provider;

import com.istudio.carmackfx.rpc.common.BaseConfig;
import com.istudio.carmackfx.rpc.provider.core.ServerContainer;
import com.istudio.carmackfx.rpc.provider.core.ServerHolder;
import com.istudio.carmackfx.rpc.provider.core.ServerRegister;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;

/**
 * Created by ACA on 2017/5/22.
 */
@Log4j(topic = "thrift server config")
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
    public ServerRegister serverRegister() {
        return new ServerRegister(this);
    }
}
