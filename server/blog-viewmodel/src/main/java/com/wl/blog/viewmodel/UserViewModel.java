package com.wl.blog.viewmodel;

import com.wl.blog.server.entity.Permission;
import com.wl.blog.server.entity.Role;
import com.wl.blog.server.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 17:58
 **/
public class UserViewModel extends User implements Serializable {
    @Size(min = 2, max = 30)
    String truePassword;
    List<RoleViewModel> roleList;
    List<PermissionViewModel> permissionList;//全部权限
    List<PermissionViewModel> menuPermissionList;//菜单权限
    List<PermissionViewModel> btnPermissionList;//按钮权限

    public List<RoleViewModel> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleViewModel> roleList) {
        this.roleList = roleList;
    }

    public List<PermissionViewModel> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionViewModel> permissionList) {
        this.permissionList = permissionList;
    }

    public List<PermissionViewModel> getMenuPermissionList() {
        return menuPermissionList;
    }

    public void setMenuPermissionList(List<PermissionViewModel> menuPermissionList) {
        this.menuPermissionList = menuPermissionList;
    }

    public List<PermissionViewModel> getBtnPermissionList() {
        return btnPermissionList;
    }

    public void setBtnPermissionList(List<PermissionViewModel> btnPermissionList) {
        this.btnPermissionList = btnPermissionList;
    }

    public String getTruePassword() {
        return truePassword;
    }

    public void setTruePassword(String truePassword) {
        this.truePassword = truePassword;
    }
}
