package com.wl.blog.server.entity;
import com.wl.common.entity.BaseObject;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * @program: server
 * @description: 角色
 * @author: WangLei
 * @create: 2019-08-06 10:47
 **/
@Entity
public class Role extends BaseObject {
    String roleName;

    int roleGrade=1;//角色等级,1为普通用户 0为管理员

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleGrade() {
        return roleGrade;
    }

    public void setRoleGrade(int roleGrade) {
        this.roleGrade = roleGrade;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Role role = (Role) obj;
        return Objects.equals(getId(),role.getId());

    }
}
