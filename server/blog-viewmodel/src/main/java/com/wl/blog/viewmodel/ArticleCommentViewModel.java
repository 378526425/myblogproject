package com.wl.blog.viewmodel;

import com.wl.blog.server.entity.ArticleComment;

/**
 * @program: server
 * @description:
 * @author: WangLei
 * @create: 2019-08-22 15:08
 **/
public class ArticleCommentViewModel extends ArticleComment {
    UserViewModel userViewModel;

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }
}
