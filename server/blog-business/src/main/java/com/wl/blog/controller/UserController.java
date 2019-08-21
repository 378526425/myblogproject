package com.wl.blog.controller;

import com.wl.blog.service.UserService;
import com.wl.blog.utils.BlogUtil;
import com.wl.blog.viewmodel.UserViewModel;
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
        request.getSession().invalidate();
        return JrsfReturn.ok();
    }

    @GetMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }

    @GetMapping("/setSession")
    public JrsfReturn setSession(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        String sessionId = request.getSession().getId();
        Map map = new HashMap();
        map.put("name", "wl");
        map.put("age", "18");
        map.put("sessionId", sessionId);
        request.getSession().setAttribute("sessionId", map);
        return JrsfReturn.okData(map);
    }

    @GetMapping("/getSession")
    public JrsfReturn getSession(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        return JrsfReturn.okData(request.getSession().getAttribute("sessionId"));
    }

    @GetMapping("/getUserByToken/{token}")
    public JrsfReturn getUserByToken(@PathVariable String token) {
        return JrsfReturn.okData(redisTemplate.opsForValue().get(token));
    }

    @GetMapping("/getUserByTokenAddTime/{token}")
    public JrsfReturn getUserByTokenAddTime(@PathVariable String token) {
        Claims claims = JWTUtils.parseJwtTokenAddTime(token);
        return JrsfReturn.okData(claims.get("user"));
    }
}
