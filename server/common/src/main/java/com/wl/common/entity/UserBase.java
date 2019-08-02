package com.wl.common.entity;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-07-25 16:04
 **/
public class UserBase extends BaseObject {
    String username;
    String password;
    String jobNumber;
    boolean disabled;

    public UserBase() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getJobNumber() {
        return this.jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}