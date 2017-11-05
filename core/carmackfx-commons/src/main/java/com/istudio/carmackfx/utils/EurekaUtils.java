package com.istudio.carmackfx.utils;

public class EurekaUtils {

    public static String getPath(String name, String version) {

        return name + ".v" + version;
    }
}
