package com.wl.blog.Interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-05 16:17
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public AuthorityInterceptor getUserInterceptor(){
        return new AuthorityInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(getUserInterceptor()).addPathPatterns(
                "/**")
                .excludePathPatterns(
                        "/register/**"
                        , "/login/**"
                );
    }
}