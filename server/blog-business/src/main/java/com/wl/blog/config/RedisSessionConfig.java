package com.wl.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-16 10:35
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600,redisFlushMode = RedisFlushMode.IMMEDIATE)
public class RedisSessionConfig {
}

