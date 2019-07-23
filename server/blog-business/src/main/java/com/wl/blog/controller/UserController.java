package com.wl.blog.controller;

import com.wl.blog.service.UserService;
import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.util.JrsfReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public JrsfReturn register(@RequestBody UserViewModel userViewModel) {
        return this.userService.register(userViewModel);
    }
}
