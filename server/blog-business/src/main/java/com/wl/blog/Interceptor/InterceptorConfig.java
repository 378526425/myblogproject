package com.wl.blog.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-05 16:17
 **/
@Component
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      /*  registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns(
                "/**")
                .excludePathPatterns("/register/**",
                        "/login/**"
                );*/
    }
}