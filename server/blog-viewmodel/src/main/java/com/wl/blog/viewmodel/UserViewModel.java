package com.wl.blog.viewmodel;

import com.wl.blog.server.entity.User;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 17:58
 **/
public class UserViewModel extends User {
    String truePassword;

    public String getTruePassword() {
        return truePassword;
    }

    public void setTruePassword(String truePassword) {
        this.truePassword = truePassword;
    }
}
