package com.wl.blog.utils;

import com.wl.blog.server.entity.User;
import com.wl.blog.viewmodel.UserViewModel;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-05 16:06
 **/
public class BlogUtil {
    public static User getLoginUser()
    {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (User) servletRequestAttributes.getRequest().getSession().getAttribute("user");
    }
}
