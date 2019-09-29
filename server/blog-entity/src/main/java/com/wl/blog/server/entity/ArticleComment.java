package com.wl.blog.server.entity;

import com.wl.common.entity.BaseObject;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @program: server
 * @description: 文章评论
 * @author: WangLei
 * @create: 2019-08-22 14:36
 **/
@Entity
public class ArticleComment extends BaseObject {
    @NotEmpty(message = "内容不可为空")
    @Size(max = 200)
    private String content;//评论的内容
    @NotNull
    private String articleId;//所属文章
    private String userId;//所属用户
    private int commentCount;//回复总数

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
