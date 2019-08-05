package com.wl.blog.server.entity;
import com.wl.common.entity.BaseObject;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * @program: server
 * @description: 文章
 * @author: WangLei
 * @create: 2019-08-05 15:33
 **/
@Entity
public class Article extends BaseObject {
    @NotBlank(message = "name 不允许为空")
    String title;
    String introduction;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition="text")
    @Size(min = 30,max=10000)
    String content;//文章内容
    String img;//封面图片
    String userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
