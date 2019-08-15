package com.wl.blog.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wl.blog.utils","com.wl","com.wl.blog.controller"})
@ServletComponentScan  //注册过滤器注解
@EnableCaching
public class BlogServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(BlogServerApplication.class, args);
    }

}
