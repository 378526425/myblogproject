package com.wl.blog.server;

import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServerApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    Logger log= LoggerFactory.getLogger(BlogServerApplicationTests.class);
    @Test
    public void contextLoads() {
        UserViewModel userViewModel=new UserViewModel();
        userViewModel.setUserName("测试");
        String uuid= UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(uuid,userViewModel,60, TimeUnit.SECONDS);
        UserViewModel  newUser= (UserViewModel) redisTemplate.opsForValue().get(uuid);
       System.out.println(newUser);
    }

}
