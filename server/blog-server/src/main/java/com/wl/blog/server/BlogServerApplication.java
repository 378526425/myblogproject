package com.wl.blog.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wl.blog.utils","com.wl","com.wl.blog.controller"})
@EnableCaching
@EnableEurekaClient
@EnableRedisHttpSession
public class BlogServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(BlogServerApplication.class, args);
    }

}
