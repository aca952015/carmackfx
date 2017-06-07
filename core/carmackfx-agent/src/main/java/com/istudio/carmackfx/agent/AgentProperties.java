package com.istudio.carmackfx.agent;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by ACA on 2017-6-7.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "carmackfx.agent")
public class AgentProperties {

    private int port = 10526;
    private int workerSize = 1;
}
