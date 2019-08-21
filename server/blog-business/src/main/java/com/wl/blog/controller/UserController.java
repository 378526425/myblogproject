package com.wl.blog.controller;

import com.wl.blog.service.UserService;
import com.wl.blog.utils.BlogUtil;
import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.exception.JrsfException;
import com.wl.common.utils.JWTUtils;
import com.wl.common.utils.JrsfReturn;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 18:19
 **/
@RestController
public class UserController {
    @Value("${server.port}")
    String port;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("register")
    public JrsfReturn register(@RequestBody @Validated UserViewModel userViewModel) {
        if (StringUtils.isEmpty(userViewModel.getUserName())) {
            return JrsfReturn.error("没有用户名！");
        }
        if (StringUtils.isEmpty(userViewModel.getLoginNumber())) {
            return JrsfReturn.error("没有登录账号！");
        }
        if (StringUtils.isEmpty(userViewModel.getPassWord())) {
            return JrsfReturn.error("没有密码！");
        }
        if (StringUtils.isEmpty(userViewModel.getTruePassword())) {
            return JrsfReturn.error("没有确认密码！");
        }
        return this.userService.register(userViewModel);
    }

    @PostMapping("login")
    public JrsfReturn login(@RequestBody @Validated UserViewModel userViewModel) {

        if (StringUtils.isEmpty(userViewModel.getLoginNumber())) {
            return JrsfReturn.error("请输入登录账号！");
        }
        if (StringUtils.isEmpty(userViewModel.getPassWord())) {
            return JrsfReturn.error("请输入密码！");
        }

        return this.userService.login(userViewModel);
    }

    @GetMapping("sso")
    public JrsfReturn sso() {
        return JrsfReturn.okData(BlogUtil.getLoginUser());
    }

    @GetMapping("loginOut")
    public JrsfReturn loginOut(HttpServletRequest request) {
        String token=request.getHeader("Authorization");
        if (token!=null)
        {
            redisTemplate.expire(token,1, TimeUnit.MILLISECONDS);
        }else
        {
            throw new  JrsfException("未登录！");
        }
        return JrsfReturn.okMsg("退出成功！");
    }

}
