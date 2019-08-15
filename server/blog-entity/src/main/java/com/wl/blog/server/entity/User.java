package com.wl.blog.server.entity;

import com.wl.common.entity.BaseObject;
import com.wl.common.entity.UserBase;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 17:55
 **/
@Entity
public class User extends BaseObject {
    @Size(min = 2, max = 15)
    String userName;
    @Size(min = 2, max = 30)
    @NotEmpty(message = "登录账号不可为空！")
    String loginNumber;
    @Size(min = 5, max = 30)
    @NotEmpty(message = "登录密码不可为空！")
    String passWord;
    /**
     * @Description: 头像
     */
    String portrait;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(String loginNumber) {
        this.loginNumber = loginNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
