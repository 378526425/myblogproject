package com.wl.blog.utils;

import com.wl.blog.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-05 16:06
 **/
@Component
public class BlogUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    static BlogUtil blogUtil;
    @PostConstruct
    public void init() {
        blogUtil = this;
        blogUtil.redisTemplate = this.redisTemplate;
    }

    public static UserViewModel getLoginUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("Authorization");
        UserViewModel userViewModel = (UserViewModel) blogUtil.redisTemplate.opsForValue().get(token);
        return userViewModel;
    }

    public static boolean conditionParamCheck(Map<String, Object> paramMap, String key) {
        return paramMap != null && paramMap.get(key) != null && !StringUtils.isEmpty(paramMap.get(key).toString());
    }

    /**
     * @Description: 获取文件的决定路径
     * @Param: 文件的相对路径
     * @return: java.lang.String
     * @Author: Mr.Wang
     * @Date: 2019/4/3
     */
    public static String fileAbsolutePath(String filePath) {
        if (filePath.indexOf(".") > 0) {//是文件，创建父路径
            filePath = BlogProperties.getProperetiesByKey("uploadPath") + File.separator + filePath;
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        } else {//是路径创建路径
            filePath = BlogProperties.getProperetiesByKey("uploadPath") + File.separator + filePath;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        return filePath;
    }
}
