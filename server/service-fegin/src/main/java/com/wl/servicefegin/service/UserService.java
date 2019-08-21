package com.wl.servicefegin.service;

import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.utils.JrsfReturn;
import com.wl.servicefegin.fallback.UserFeignFallBack;
import com.wl.servicefegin.interceptor.FeignHystrixConcurrencyStrategy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "service-blog",fallback = UserFeignFallBack.class,configuration = FeignHystrixConcurrencyStrategy.class)
@Component
public interface UserService {
    @GetMapping(value = "/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
    @PostMapping("register")
     JrsfReturn register(@RequestBody @Validated UserViewModel userViewModel) ;
    @PostMapping("login")
     JrsfReturn login(@RequestBody @Validated UserViewModel userViewModel);
    @GetMapping("sso")
    JrsfReturn sso();
    @GetMapping("loginOut")
    JrsfReturn loginOut();
    @GetMapping("setSession")
    JrsfReturn setSession();
    @GetMapping("getSession")
    JrsfReturn getSession();
    @GetMapping("/getUserByToken/{token}")
    JrsfReturn getUserByToken(@PathVariable String token);
    @GetMapping("/getUserByTokenAddTime/{token}")
    JrsfReturn getUserByTokenAddTime(@PathVariable String token);
}
