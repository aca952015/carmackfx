package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.agent.impls.DefaultCallbackService;
import com.istudio.carmackfx.agent.impls.DefaultClientManager;
import com.istudio.carmackfx.agent.processors.ActorMessageProcessor;
import com.istudio.carmackfx.agent.processors.InternalMessageProcessor;
import com.istudio.carmackfx.agent.processors.SecurityMessageProcessor;
import com.istudio.carmackfx.agent.processors.PublicMessageProcessor;
import com.istudio.carmackfx.callback.CallbackService;
import com.istudio.carmackfx.callback.ClientManager;
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
    public SecurityMessageProcessor securityMessageProcessor() {
        return new SecurityMessageProcessor();
    }

    @Bean
    public PublicMessageProcessor publicMessageProcessor() {
        return new PublicMessageProcessor();
    }

    @Bean
    public InternalMessageProcessor internalMessageProcessor() {
        return new InternalMessageProcessor();
    }

    @Bean
    public ActorMessageProcessor actorMessageProcessor() {
        return new ActorMessageProcessor();
    }

    @Bean
    public MessageProcessorManager messageProcessorManager() {
        return new MessageProcessorManager();
    }

    @Bean
    public SessionManager sessionManager() {
        return new SessionManager();
    }

    @Bean
    public ClientManager clientManager() {
        return new DefaultClientManager();
    }

    @Bean
    public CallbackService callbackService() {
        return new DefaultCallbackService();
    }

    @Bean
    public AgentHolder agentHolder() {

        return new AgentHolder();
    }
}