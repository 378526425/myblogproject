package com.wl.blog.controller;
import com.wl.blog.service.UserService;
import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.utils.JrsfReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 18:19
 **/
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("register")
    public JrsfReturn register(@RequestBody @Validated UserViewModel userViewModel) {
        if (StringUtils.isEmpty(userViewModel.getUserName()))
        {
            return JrsfReturn.error("没有用户名！");
        }
        if (StringUtils.isEmpty(userViewModel.getLoginNumber()))
        {
            return JrsfReturn.error("没有登录账号！");
        }
        if (StringUtils.isEmpty(userViewModel.getPassWord()))
        {
            return JrsfReturn.error("没有密码！");
        }
        if (StringUtils.isEmpty(userViewModel.getTruePassword()))
        {
            return JrsfReturn.error("没有确认密码！");
        }
        return this.userService.register(userViewModel);
    }
    @PostMapping("login")
    public JrsfReturn login(@RequestBody @Validated UserViewModel userViewModel, HttpServletRequest request)
    {

        if (StringUtils.isEmpty(userViewModel.getLoginNumber()))
        {
            return JrsfReturn.error("请输入登录账号！");
        }
        if (StringUtils.isEmpty(userViewModel.getPassWord()))
        {
            return JrsfReturn.error("请输入密码！");
        }

        return this.userService.login(userViewModel,request);
    }
    @GetMapping("loginOut")
    public JrsfReturn loginOut(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return JrsfReturn.ok();
    }

}
