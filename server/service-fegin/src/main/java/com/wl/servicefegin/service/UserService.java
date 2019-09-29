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
    @PostMapping("/api/register")
     JrsfReturn register(@RequestBody @Validated UserViewModel userViewModel) ;
    @PostMapping("/api/login")
     JrsfReturn login(@RequestBody @Validated UserViewModel userViewModel);
    @GetMapping("/api/sso")
    JrsfReturn sso();
    @GetMapping("/api/loginOut")
    JrsfReturn loginOut();

}
