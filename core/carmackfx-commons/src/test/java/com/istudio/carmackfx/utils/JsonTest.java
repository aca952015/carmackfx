package com.istudio.carmackfx.utils;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class JsonTest {

    @Test
    public void parse() {

        String json = "{\"ServiceName\":\"RoomService\",\"MethodName\":\"Chat\",\"Arguments\":[{\"ArgumentName\":\"msg\",\"ArgumentValue\":\"\\\"大家好\\\"\",\"IsValueType\":false}]}";

        JSON.parse(json);
    }
}
