package com.wl.blog.Interceptor;

import com.alibaba.fastjson.JSON;
import com.querydsl.jpa.impl.JPAQuery;
import com.wl.blog.server.entity.Article;
import com.wl.blog.server.entity.querydsl.QArticle;
import com.wl.blog.utils.BlogUtil;
import com.wl.common.HttpUtils.RequestWrapper;
import com.wl.common.dao.BaseDao;
import com.wl.common.entity.BaseObject;
import com.wl.common.enummodel.AccessType;
import com.wl.common.utils.EvCommonTool;
import com.wl.common.utils.JrsfReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String token=request.getHeader("Authorization");
        if (token!=null)
        {
            long times= redisTemplate.getExpire(token, TimeUnit.SECONDS);//剩余过期时间
            if (times>0)
            {
                redisTemplate.expire(token,60,TimeUnit.SECONDS);//如果还没有过期则续期60秒
            }else
            {
                redisTemplate.delete(token);
            }
        }

        String method = request.getMethod();
        String uri = request.getRequestURI();
        if (uri.startsWith("/api/")) {
            uri = uri.substring(5);
        }
        String resource = uri;
        AccessType aType = getAccessType(method);
        String id = "";
        if (AccessType.DELETE.equals(aType)) {
            id = uri.substring(uri.lastIndexOf("/") + 1);
        }else if (AccessType.UPDATE.equals(aType))
        {
            RequestWrapper requestWrapper = new RequestWrapper(request);
            String body = requestWrapper.getBody();
            id= JSON.parseObject(body, BaseObject.class).getId();
        }
        if (uri.indexOf("/") >= 0) {
            resource = uri.substring(0, uri.indexOf("/"));
        }
        //文章的添加，修改，删除需要登录才能操作,且用户只能修改和删除自己的文章
        if (!articleCheck(resource,aType,id,response))
            return false;

        return true;
    }

    private boolean articleCheck(String resource, AccessType aType, String id,HttpServletResponse response) {
        if ("Article".equals(resource) && (!AccessType.REVIEW.equals(aType) && BlogUtil.getLoginUser() == null)) {
            EvCommonTool.responseInfo(response, JrsfReturn.error("未登录"));
            return false;
        } else if ("Article".equals(resource) && AccessType.UPDATE.equals(aType)||AccessType.DELETE.equals(aType)) {
            if (BlogUtil.getLoginUser().getRoleList().get(0).getRoleGrade()==0)
            {
                return true;//管理员有全部权限
            }
            QArticle qArticle=QArticle.article;
            JPAQuery<Article> jpaQuery=new JPAQuery<>(this.baseDao.getEntityManager());
            Article article= jpaQuery.select(qArticle).from(qArticle).where(qArticle.id.eq(id)).fetchFirst();

            if (article!=null&&!BlogUtil.getLoginUser().getId().equals(article.getUserId())) {
                EvCommonTool.responseInfo(response, JrsfReturn.error("权限不足!无法修改或删除他人的文章！"));
                return false;
            }
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
