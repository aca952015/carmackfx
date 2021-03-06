package com.istudio.carmackfx.agent;

import com.istudio.carmackfx.annotation.TProcessor;
import com.istudio.carmackfx.protocol.MessageType;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ACA on 2017/6/8.
 */
@Slf4j
public class MessageProcessorManager {

    private final Map<MessageType, MessageProcessor> processors;

    public MessageProcessorManager() {

        processors = new HashMap<>();
    }

    public MessageProcessor getProcessor(MessageType type) {

        if(!processors.containsKey(type)) {

            return null;
        }

        return processors.get(type);
    }

    public void register(Collection<MessageProcessor> results) {

        for(MessageProcessor processor : results) {

            TProcessor ann = processor.getClass().getAnnotation(TProcessor.class);
            if(ann != null) {

                log.info("Processor loaded: {}", ann.type().name());

                processor.init();

                processors.put(ann.type(), processor);
            }
        }
    }
}
