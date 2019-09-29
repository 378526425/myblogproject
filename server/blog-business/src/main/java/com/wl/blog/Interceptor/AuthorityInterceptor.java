package com.wl.blog.Interceptor;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.wl.blog.server.entity.LoginStatus;
import com.wl.blog.server.entity.querydsl.QIgnoreResource;
import com.wl.blog.server.entity.querydsl.QLoginStatus;
import com.wl.blog.utils.BlogUtil;
import com.wl.blog.viewmodel.IgnoreResourceViewModel;
import com.wl.blog.viewmodel.PermissionViewModel;
import com.wl.common.dao.BaseDao;
import com.wl.common.enummodel.AccessType;
import com.wl.common.utils.EvCommonTool;
import com.wl.common.utils.JrsfReturn;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: server
 * @description: 资源权限拦截
 * @author: WangLei
 * @create: 2019-08-05 16:04
 **/
public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    BaseDao baseDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/")) {
            uri = uri.substring(5);
        }
        String resource = uri;
        AccessType aType = getAccessType(method);
        if (uri.indexOf("/") >= 0) {
            resource = uri.substring(0, uri.indexOf("/"));
        }

        if (checkIgnore(resource, aType, request))//校验通用不需要拦截的接口
        {
            return true;
        }
        String token = request.getHeader("Authorization");
        //验证是否登录
        if (token != null) {
            long times = redisTemplate.getExpire(token, TimeUnit.SECONDS);//剩余过期时间
            if (times > 0) {
                redisTemplate.expire(token, 5, TimeUnit.MINUTES);//如果还没有过期则续期5分钟
            } else {
                redisTemplate.delete(token);
                EvCommonTool.responseInfo(response, JrsfReturn.error("登录失效！"));
                return false;
            }
        } else {
            EvCommonTool.responseInfo(response, JrsfReturn.error("未登录！"));
            return false;
        }

        if (!checkLoginStatus(response))//验证当前用户是否处于登录状态
            return false;

        if (!checkPermission(resource, aType))//校验是否有该权限
            return false;

        return true;
    }

    private boolean checkIgnore(String resource, AccessType aType, HttpServletRequest request) {
        ServletContext applicationContext = request.getSession().getServletContext();
        Object object = applicationContext.getAttribute("ignoreResource");
        List<PermissionViewModel> permissionViewModelList = new ArrayList<>();
        if (object != null) {
            permissionViewModelList = (List<PermissionViewModel>) object;
        } else {
            QIgnoreResource qIgnoreResource = QIgnoreResource.ignoreResource;

            JPAQuery<IgnoreResourceViewModel> jpaQuery = new JPAQuery<>(this.baseDao.getEntityManager());
            List<IgnoreResourceViewModel> ignoreResourceViewModelList= jpaQuery.from(qIgnoreResource).select(
                    Projections.bean(IgnoreResourceViewModel.class
                            , qIgnoreResource.resourceName
                            , qIgnoreResource.aType)
            ).fetch();
            for (IgnoreResourceViewModel model :ignoreResourceViewModelList)
            {
                PermissionViewModel permissionViewModel=new PermissionViewModel();
                permissionViewModel.setResources(model.getResourceName());
                permissionViewModel.setRequestType(model.getaType());
                permissionViewModelList.add(permissionViewModel);
            }
            applicationContext.setAttribute("ignoreResource",permissionViewModelList);
        }
        return checkPermissionByPermissList(resource,aType,permissionViewModelList);
    }

    private boolean checkPermission(String resource, AccessType aType) {
        List<PermissionViewModel> permissionViewModelList = BlogUtil.getLoginUser().getBtnPermissionList();
        return checkPermissionByPermissList(resource,aType,permissionViewModelList);
    }
   private boolean checkPermissionByPermissList(String resource, AccessType aType,List<PermissionViewModel> permissionViewModelList)
   {
       for (PermissionViewModel model : permissionViewModelList) {
           if (model.getResources() != null && model.getRequestType() != null) {
               String[] resourceNames = model.getResources().split("#");
               String[] aTypes = model.getRequestType().split("#");
               for (int i = 0; i < resourceNames.length && i < aTypes.length; i++) {
                   if (resourceNames[i].equals(resource) && aType.toString().equals(aTypes[i])) {
                       return true;
                   }
               }
           }
       }
       return false;
   }
    private boolean checkLoginStatus(HttpServletResponse response) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String sessionId = request.getSession().getId();
        QLoginStatus qLoginStatus = QLoginStatus.loginStatus;
        JPAQuery<LoginStatus> jpaQuery = new JPAQuery<>(this.baseDao.getEntityManager());
        long userSession = jpaQuery.select(qLoginStatus).from(qLoginStatus).where(qLoginStatus.sessionId.eq(sessionId).and(qLoginStatus.userName.eq(BlogUtil.getLoginUser().getLoginNumber()))).fetchCount();
        if (userSession == 0) {
            EvCommonTool.responseInfo(response, JrsfReturn.error("在其他地方已登录！"));
            return false;//当前用户已被挤下线
        }
        return true;
    }

    public static AccessType getAccessType(String method) {
        byte var3 = -1;
        switch (method.hashCode()) {
            case 70454:
                if (method.equals("GET")) {
                    var3 = 0;
                }
                break;
            case 79599:
                if (method.equals("PUT")) {
                    var3 = 3;
                }
                break;
            case 2461856:
                if (method.equals("POST")) {
                    var3 = 1;
                }
                break;
            case 2012838315:
                if (method.equals("DELETE")) {
                    var3 = 2;
                }
        }

        switch (var3) {
            case 0:
                return AccessType.REVIEW;
            case 1:
                return AccessType.CREATE;
            case 2:
                return AccessType.DELETE;
            case 3:
                return AccessType.UPDATE;
            default:
                return AccessType.REVIEW;
        }
    }
}
