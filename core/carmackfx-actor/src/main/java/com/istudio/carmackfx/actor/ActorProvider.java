package com.istudio.carmackfx.actor;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
public class ActorProvider extends AbstractExtensionId<ActorProvider.SpringExt> {

    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }

    public static class SpringExt implements Extension {
        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public Props props(String actorBeanName) {
            return Props.create(ActorProducer.class, applicationContext, actorBeanName);
        }
    }
}
