package com.wl.blog.utils;

import com.wl.blog.server.entity.User;
import com.wl.blog.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.util.Map;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-05 16:06
 **/
public class BlogUtil {

    public static UserViewModel getLoginUser()
    {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (UserViewModel) servletRequestAttributes.getRequest().getSession().getAttribute("user");
    }
    public static boolean conditionParamCheck(Map<String,Object> paramMap,String key)
    {
        return paramMap!=null&& paramMap.get(key)!=null&&!StringUtils.isEmpty(paramMap.get(key).toString());
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
