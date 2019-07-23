package com.wl.blog.server.entity;

import com.wl.common.entity.BaseObject;

import javax.persistence.Entity;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 17:55
 **/
@Entity
public class User extends BaseObject{
    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
