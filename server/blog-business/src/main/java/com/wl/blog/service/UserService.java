package com.wl.blog.service;

import com.wl.blog.server.entity.User;
import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.dao.BaseDao;
import com.wl.common.util.JrsfReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 18:00
 **/
@Service
public class UserService {
    @Autowired
    BaseDao dao;

    @Transactional
    public JrsfReturn register(UserViewModel userViewModel) {
        User user = new User();
        BeanUtils.copyProperties(userViewModel, user);
        user.setId(UUID.randomUUID().toString());
        this.dao.save(user);
        return JrsfReturn.okData(user);
    }
}
