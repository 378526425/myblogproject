package com.wl.blog.server.entity;

import com.wl.common.entity.BaseObject;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @program: server
 * @description: 评论中的留言
 * @author: WangLei
 * @create: 2019-08-22 15:04
 **/
@Entity
public class CommentMessage  extends BaseObject {
    @NotEmpty(message = "内容不可为空")
    @Size(max = 200)
    private String content;//内容
    private String userId;//所属用户
    @NotNull
    private String toUserId;//对哪个用户说的
    @NotNull
    private String commentId;//所属评论

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
