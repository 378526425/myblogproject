package com.wl.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-15 13:37
 **/
public class AppProperties {
    private static String viewModelPackage;
    private static String servicePackage;
    private static String controllerPackage;
    private static Map<String, String> propMap = new HashMap();

    public AppProperties() {
    }

    public static String getViewModelPackage() {
        return viewModelPackage;
    }

    public static void setViewModelPackage(String viewModelPackage) {
        viewModelPackage = viewModelPackage;
    }

    public static String getServicePackage() {
        return servicePackage;
    }

    public static void setServicePackage(String servicePackage) {
        servicePackage = servicePackage;
    }

    public static String getControllerPackage() {
        return controllerPackage;
    }

    public static void setControllerPackage(String controllerPackage) {
        controllerPackage = controllerPackage;
    }

    public static Map<String, String> getPropMap() {
        return propMap;
    }
}