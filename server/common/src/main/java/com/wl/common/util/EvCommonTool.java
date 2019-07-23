package com.wl.common.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 18:13
 **/
public class EvCommonTool {
    public static String getExceptionMsg(Exception ex) {
        if (ex != null) {
            if (ex instanceof InvocationTargetException) {
                Throwable t = ((InvocationTargetException)ex).getTargetException();
                return StringUtils.isEmpty(t.getMessage()) ? t.toString() : t.getMessage();
            } else {
                return StringUtils.isEmpty(ex.getMessage()) ? ex.toString() : ex.getMessage();
            }
        } else {
            return "";
        }
    }
}
