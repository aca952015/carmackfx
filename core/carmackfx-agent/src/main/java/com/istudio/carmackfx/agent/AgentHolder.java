package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.commons.base.BaseHolder;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ACA on 2017-6-7.
 */
@Log4j
public class AgentHolder extends BaseHolder {

    @Autowired
    private AgentProperties properties;

    @Autowired
    private AgentServer agentServer;

    @Override
    protected void doInit() {

        agentServer.start();

        log.info(String.format("Start Agent Server at %d.", properties.getPort()));
    }
}
