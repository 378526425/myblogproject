package com.wl.common.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 17:41
 **/
@Entity
@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
public class BaseObject{
    @Id
    @Column(
            length = 36
    )
    private String id;
    @Column(
            length = 36
    )
    private String creator;
    @Column(
            length = 36
    )
    private String modifier;
    @Column(
            name = "createdtime"
    )
    private Date createdTime;
    @Column(
            name = "modifiedtime"
    )
    private Date modifiedTime;

    public BaseObject() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}