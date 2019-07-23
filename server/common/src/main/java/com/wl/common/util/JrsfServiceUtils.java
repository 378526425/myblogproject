package com.wl.common.util;

import com.wl.common.enummodel.JrsfEnum;
import com.wl.common.exception.JrsfException;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

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
