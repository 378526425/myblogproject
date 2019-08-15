package com.wl.blog.server.entity;

import com.wl.common.entity.BaseObject;

import javax.persistence.Entity;

/**
 * @program: server
 * @description:角色权限中间表
 * @author: WangLei
 * @create: 2019-08-06 11:04
 **/
@Entity
public class RoleToPermission extends BaseObject {
    String roleId;
    String permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
