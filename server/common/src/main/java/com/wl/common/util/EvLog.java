package com.wl.common.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 18:10
 **/
public class EvLog {
    private static final Logger LOG = LoggerFactory.getLogger(EvLog.class);

    public EvLog() {
    }

    public static void info(String msg) {
        LOG.info("{}", msg);
    }

    public static void error(String msg) {
        LOG.error("{}", msg);
    }

    public static void error(Exception ex) {
        ex.printStackTrace();
        error(EvCommonTool.getExceptionMsg(ex));
    }

    public static void warn(String msg) {
        LOG.warn("{}", msg);
    }

    public static void debug(String msg) {
        LOG.debug("{}", msg);
    }

    public static void beforeReturn(String postMsg) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            StringBuilder urlSb = new StringBuilder();
            urlSb.append(" beforeReturn ");
            urlSb.append(" Authorization " + request.getHeader("Authorization"));
            urlSb.append(" IP " + request.getRemoteAddr());
            urlSb.append(" Port " + request.getRemotePort());
            urlSb.append(" URL " + request.getRequestURL());
            urlSb.append(" Method " + request.getMethod());
            Enumeration enu = request.getParameterNames();

            while(enu.hasMoreElements()) {
                String name = (String)enu.nextElement();
                urlSb.append(String.format(" name:{},value:{}", name, request.getParameter(name)));
            }

            debug(urlSb.toString());
            if (!StringUtils.isEmpty(postMsg)) {
                debug(postMsg);
            }
        }

    }

    public static void afterReturn(Object returnMsg) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            StringBuilder urlSb = new StringBuilder();
            urlSb.append(" afterReturn ");
            urlSb.append(" Authorization " + request.getHeader("Authorization"));
            urlSb.append(" IP " + request.getRemoteAddr());
            urlSb.append(" Port " + request.getRemotePort());
            urlSb.append(" URL " + request.getRequestURL());
            urlSb.append(" Method " + request.getMethod());
            Enumeration enu = request.getParameterNames();

            while(enu.hasMoreElements()) {
                String name = (String)enu.nextElement();
                urlSb.append(String.format(" name:{},value:{}", name, request.getParameter(name)));
            }

            debug(urlSb.toString());
            if (returnMsg instanceof String) {
                if (returnMsg != null) {
                    debug(returnMsg.toString());
                }
            } else if (returnMsg != null) {
                debug(JSONObject.toJSONString(returnMsg));
            }
        }

    }
}