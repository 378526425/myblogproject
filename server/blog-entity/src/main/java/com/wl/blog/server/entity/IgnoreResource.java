package com.wl.blog.server.entity;

import com.wl.common.entity.BaseObject;

import javax.persistence.Entity;

/**
 * @program: server
 * @description: 不需要验证的资源
 * @author: WangLei
 * @create: 2019-09-29 09:26
 **/
@Entity
public class IgnoreResource extends BaseObject {
    String resourceName;
    String aType;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }
}
