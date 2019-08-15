package com.wl.blog.viewmodel;

import com.wl.blog.server.entity.Permission;
import com.wl.blog.server.entity.Role;
import com.wl.blog.server.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 17:58
 **/
public class UserViewModel extends User {
    @Size(min = 2, max = 30)
    String truePassword;
    List<Role> roleList;
    List<Permission> permissionList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public String getTruePassword() {
        return truePassword;
    }

    public void setTruePassword(String truePassword) {
        this.truePassword = truePassword;
    }
}
