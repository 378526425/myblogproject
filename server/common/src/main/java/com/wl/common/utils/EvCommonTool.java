package com.wl.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    public static void responseInfo(HttpServletResponse response,JrsfReturn ret) {
        String json = JSONObject.toJSONString(ret);

        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(json);
            out.flush();
        } catch (IOException e) {
            EvLog.error(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
