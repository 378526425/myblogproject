package com.wl.servicefegin.controller;

import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.utils.JrsfReturn;
import com.wl.servicefegin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-15 14:30
 **/
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public JrsfReturn register(@RequestBody @Validated UserViewModel userViewModel) {
        return this.userService.register(userViewModel);
    }

    @PostMapping("/login")
    public JrsfReturn login(@RequestBody @Validated UserViewModel userViewModel) {
        return this.userService.login(userViewModel);
    }

    @GetMapping("/sso")
    public JrsfReturn sso() {
        return this.userService.sso();
    }

    @GetMapping("/loginOut")
    public JrsfReturn loginOut() {
        return this.userService.loginOut();
    }

}
