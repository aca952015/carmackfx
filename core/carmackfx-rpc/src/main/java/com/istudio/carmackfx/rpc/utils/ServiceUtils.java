package com.istudio.carmackfx.rpc.utils;

import com.istudio.carmackfx.annotation.TService;
import com.istudio.carmackfx.model.consts.ConfigConsts;
import org.springframework.util.StringUtils;

/**
 * Created by ACA on 2017/5/31.
 */
public class ServiceUtils {

    public static String getName(Class iface) {

        return iface.getName();
    }

    public static String getGroup(Class iface) {

        if(iface.isAnnotationPresent(TService.class)) {

            TService ann = (TService)iface.getAnnotation(TService.class);
            return StringUtils.isEmpty(ann.group()) ? ConfigConsts.SERVICE_BASE_GROUP : ann.group();
        }

        return ConfigConsts.SERVICE_BASE_GROUP;
    }

    public static String getVersion(Class iface) {

        if(iface.isAnnotationPresent(TService.class)) {

            TService ann = (TService)iface.getAnnotation(TService.class);
            return StringUtils.isEmpty(ann.version()) ? ConfigConsts.SERVICE_BASE_VERSION : ann.version();
        }

        return ConfigConsts.SERVICE_BASE_VERSION;
    }

    public static boolean isTService(Class iface) {

        return iface.isAnnotationPresent(TService.class);
    }
}
