package com.wl.blog.viewmodel;

import com.wl.blog.server.entity.User;

import javax.validation.constraints.Size;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 17:58
 **/
public class UserViewModel extends User {
    @Size(min = 2,max =30 )
    String truePassword;

    public String getTruePassword() {
        return truePassword;
    }

    public void setTruePassword(String truePassword) {
        this.truePassword = truePassword;
    }
}
