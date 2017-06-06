package com.istudio.carmackfx.rpc.utils;

import com.istudio.carmackfx.rpc.annotation.TService;
import com.istudio.carmackfx.rpc.common.Consts;
import org.springframework.util.StringUtils;

/**
 * Created by ACA on 2017/5/31.
 */
public class ServiceUtils {

    public static String getName(Class iface) {
        return iface.getName() + "/v" + getVersion(iface);
    }

    public static String getGroup(Class iface) {

        if(iface.isAnnotationPresent(TService.class)) {

            TService ann = (TService)iface.getAnnotation(TService.class);
            return StringUtils.isEmpty(ann.group) ? Consts.SERVICE_BASE_GROUP : ann.group;
        }

        return Consts.SERVICE_BASE_GROUP;
    }

    public static String getVersion(Class iface) {

        if(iface.isAnnotationPresent(TService.class)) {

            TService ann = (TService)iface.getAnnotation(TService.class);
            return StringUtils.isEmpty(ann.version) ? Consts.SERVICE_BASE_VERSION : ann.version;
        }

        return Consts.SERVICE_BASE_VERSION;
    }

    public static boolean isTService(Class iface) {

        return iface.isAnnotationPresent(TService.class);
    }
}
