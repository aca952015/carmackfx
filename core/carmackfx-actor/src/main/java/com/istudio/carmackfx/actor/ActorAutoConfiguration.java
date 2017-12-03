package com.istudio.carmackfx.actor;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ACA
 * @create: 2017-12-03
 **/
@Configuration
@Slf4j
public class ActorAutoConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem(Config akkaConfiguration, ActorProvider actorProvider) {

        ActorSystem actorSystem = ActorSystem.create(ActorConsts.SYSTEM_NAME, akkaConfiguration);
        actorProvider.get(actorSystem).initialize(applicationContext);

        log.info("context system loaded.");

        return actorSystem;
    }

    @Bean
    public ActorProvider actorProvider() {
        return new ActorProvider();
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }

    @Bean
    public ActorManager actorManager() {
        return new ActorManager();
    }
}