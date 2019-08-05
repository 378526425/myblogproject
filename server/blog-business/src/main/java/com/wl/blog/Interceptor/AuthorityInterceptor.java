package com.wl.blog.Interceptor;

import com.wl.blog.utils.BlogUtil;
import com.wl.common.utils.EvCommonTool;
import com.wl.common.utils.JrsfReturn;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: server
 * @description: 资源权限拦截
 * @author: WangLei
 * @create: 2019-08-05 16:04
 **/
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (BlogUtil.getLoginUser()==null)
        {
            EvCommonTool.responseInfo(response, JrsfReturn.error("未登录"));
            return false;
        }else
        {
            return true;
        }
    }
}
