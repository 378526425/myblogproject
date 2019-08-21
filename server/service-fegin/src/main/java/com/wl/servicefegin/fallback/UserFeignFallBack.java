package com.wl.servicefegin.fallback;

import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.utils.JrsfReturn;
import com.wl.servicefegin.service.UserService;
import org.springframework.stereotype.Component;
/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-16 15:04
 **/
@Component
public class UserFeignFallBack implements UserService {
    @Override
    public JrsfReturn register(UserViewModel userViewModel) {
        return null;
    }

    @Override
    public JrsfReturn login(UserViewModel userViewModel) {
        return null;
    }

    @Override
    public JrsfReturn sso() {
        return null;
    }

    @Override
    public JrsfReturn loginOut() {
        return null;
    }
}
