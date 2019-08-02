package com.wl.common.utils;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 18:04
 **/
public class JrsfServiceUtils {
    public static JrsfReturn getExpCodeMsg(Exception exp) {
      return JrsfReturn.error(EvCommonTool.getExceptionMsg(exp));

    }
}
