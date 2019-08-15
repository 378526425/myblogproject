package com.wl.blog.utils;

import com.wl.common.utils.AppProperties;
import com.wl.common.utils.EvCommonTool;
import com.wl.common.utils.EvLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-12 18:11
 **/
@Configuration
public class BlogProperties extends AppProperties {
    private static Map<String, String> enassPropMap = new HashMap<>();
    @Value("${defaultrole}")
    private  String defaultrole;
    @Value("${spring.servlet.upload-path}")
    private  String uploadPath;

    public String getDefaultrole() {
        return defaultrole;
    }

    public void setDefaultrole(String defaultrole) {
        this.defaultrole = defaultrole;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
    @PostConstruct
    public void initProp(){
        try {
            //EnassProperties enassProperties = SpringContextUtils.getBean(EnassProperties.class);
            for (Field f:BlogProperties.class.getDeclaredFields()){
                Method m = EvCommonTool.getMethodIgnoreCase(BlogProperties.class,"get"+f.getName(),true);
                if (m!=null){
                    try {
                        enassPropMap.put(f.getName(), EvCommonTool.toString(m.invoke(this)));
                    } catch (Exception ex) {

                    }
                }
            }
        } catch (Exception e) {
            EvLog.error(e);
        }
    }
    public static String getProperetiesByKey(String key)
    {
        return enassPropMap.get(key);
    }
}
