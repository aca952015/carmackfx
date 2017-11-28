package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.agent.processors.SecurityMessageProcessor;
import com.istudio.carmackfx.config.BaseHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by ACA on 2017-6-7.
 */
@Slf4j
public class AgentHolder extends BaseHolder {

    @Autowired
    private AgentProperties properties;

    @Autowired
    private AgentServer agentServer;

    @Autowired
    private MessageProcessorManager messageProcessorManager;

    @Override
    protected void doInit() {

        log.info(String.format("Start Agent Server at %d.", properties.getPort()));

        Map<String, MessageProcessor> results = getContext().getBeansOfType(MessageProcessor.class);

        messageProcessorManager.register(results.values());

        agentServer.start();
    }
}
