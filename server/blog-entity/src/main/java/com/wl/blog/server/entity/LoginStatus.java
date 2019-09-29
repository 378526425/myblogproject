package com.wl.blog.server.entity;
import com.wl.common.entity.BaseObject;

import javax.persistence.Entity;

/**
 * @program: yhxdmes
 * @description: 登录状态判断，用作挤号操作
 * @author: WangLei
 * @create: 2019-08-26 17:13
 **/
@Entity
public class LoginStatus extends BaseObject {
    String userName;
    String sessionId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
