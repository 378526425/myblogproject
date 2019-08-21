package com.wl.blog.viewmodel;

import com.wl.blog.server.entity.Article;

import java.io.Serializable;


/**
 * @program: server
 * @description: 文章
 * @author: WangLei
 * @create: 2019-08-05 15:43
 **/
public class ArticleViewModel extends Article implements Serializable {
    String base64;
    String imgName;
    UserViewModel userViewModel;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }
}
