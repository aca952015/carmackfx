package com.istudio.carmackfx.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author: ACA
 * @create: 2017-12-3
 **/
@Slf4j
public class ActorManager {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private ActorProvider actorProvider;

    public ActorRef query(String actorType, String actorId) throws Exception {

        Timeout timeout = new Timeout(5, TimeUnit.SECONDS);
        ActorSelection selection = actorSystem.actorSelection("/user/" + getRealId(actorType, actorId));
        Future<ActorRef> future = selection.resolveOne(timeout);

        return Await.result(future, timeout.duration());
    }

    public <T extends CarmackContext> T find(Class<T> actorType, String actorId) {

        try {

            ActorRef ref = query(actorType.getSimpleName(), actorId);

            if(ref != null) {

                log.info("actor {} found, path: {}", actorType.getSimpleName(), ref.path());

                return proxy(ref, actorType);
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public <T extends CarmackContext> T create(Class<T> actorType, String actorId) {

        try {

            ActorRef ref = actorSystem.actorOf(actorProvider.get(actorSystem).props(actorType.getSimpleName()), getRealId(actorType.getSimpleName(), actorId));

            log.info("actor {} created, path: {}", actorType.getSimpleName(), ref.path());

            return proxy(ref, actorType);
        } catch(Exception ex) {
            return find(actorType, actorId);
        }
    }

    private <T extends CarmackContext> T proxy(ActorRef ref, Class<T> classType) {

        ActorInstance instance =  new ActorInstance(ref);

        ActorProxy proxy = new ActorProxy(instance);

        return (T) Proxy.newProxyInstance(classType.getClassLoader(), new Class[]{ classType }, proxy);
    }

    private String getRealId(String actorType, String shortId) {

        return actorType + "-" + shortId;
    }
}
