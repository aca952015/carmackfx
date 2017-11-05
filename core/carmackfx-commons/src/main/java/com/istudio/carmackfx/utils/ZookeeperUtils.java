package com.istudio.carmackfx.utils;

public class ZookeeperUtils {

    public static String getPath(String name, String version) {

        return name + "/v" + version;
    }
}
