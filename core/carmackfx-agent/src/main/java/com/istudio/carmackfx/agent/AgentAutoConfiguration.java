package com.istudio.carmackfx.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ACA on 2017-6-7.
 */
@Configuration
@EnableConfigurationProperties(AgentProperties.class)
public class AgentAutoConfiguration {

    @Autowired
    private AgentProperties properties;

    @Bean
    public AgentServer agentServer(){

        AgentServer server = new AgentServer(properties);
        server.noDelay(1, 10, 2, 1);
        server.setMinRto(10);
        server.wndSize(64, 64);
        server.setTimeout(10 * 1000);
        server.setMtu(512);

        return server;
    }

    @Bean
    public AgentHolder agentHolder() {

        return new AgentHolder();
    }
}