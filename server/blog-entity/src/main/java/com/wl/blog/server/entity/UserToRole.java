package com.wl.blog.server.entity;

import com.wl.common.entity.BaseObject;

import javax.persistence.Entity;

/**
 * @program: server
 * @description: 用户角色联系表
 * @author: WangLei
 * @create: 2019-08-06 11:08
 **/
@Entity
public class UserToRole extends BaseObject {
    String userId;
    String roleId="client";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
