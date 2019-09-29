package com.wl.blog.viewmodel;

import com.wl.blog.server.entity.CommentMessage;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-22 15:09
 **/
public class CommentMessageViewModel extends CommentMessage {
    UserViewModel userViewModel;
    UserViewModel toUserViewModel;

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }

    public UserViewModel getToUserViewModel() {
        return toUserViewModel;
    }

    public void setToUserViewModel(UserViewModel toUserViewModel) {
        this.toUserViewModel = toUserViewModel;
    }
}
