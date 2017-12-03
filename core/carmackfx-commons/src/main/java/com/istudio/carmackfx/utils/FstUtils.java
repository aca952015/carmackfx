package com.istudio.carmackfx.utils;

import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

/**
 * Created by ACA on 2017-5-26.
 */
public class FstUtils {
    static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    public static Object deserialize(byte[] buffer, Class clazz) throws Exception {

        ByteArrayInputStream stream = new ByteArrayInputStream(buffer);
        FSTObjectInput in = conf.getObjectInput(stream);
        Object result = in.readObject(clazz);
        // DON'T: in.close(); here prevents reuse and will result in an exceptions
        stream.close();
        return result;
    }

    public static Object deserialize(ByteBuffer buffer, Class clazz) throws Exception {

        byte[] bufferData = new byte[buffer.remaining()];
        buffer.get(bufferData);

        return deserialize(bufferData, clazz);
    }

    public static byte[] serialize(Object object) throws Exception {

        return conf.asByteArray(object);
    }
}
