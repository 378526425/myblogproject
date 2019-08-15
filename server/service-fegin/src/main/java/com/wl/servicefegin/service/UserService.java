package com.wl.servicefegin.service;

import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.utils.JrsfReturn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "service-blog")
@Component
public interface UserService {
    @GetMapping(value = "/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
    @PostMapping("register")
     JrsfReturn register(@RequestBody @Validated UserViewModel userViewModel) ;
    @PostMapping("login")
     JrsfReturn login(@RequestBody @Validated UserViewModel userViewModel);
    @GetMapping("loginOut")
    JrsfReturn loginOut(HttpServletRequest request);

}
