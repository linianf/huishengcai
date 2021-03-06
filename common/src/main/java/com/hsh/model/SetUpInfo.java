package com.hsh.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SetUpInfo {

    private int id;

    private String name;

    private String content;

    // 操作人
    @Column(name = "user_id")
    private long userId;

    // 操作时间
    @Column(name = "create_time")
    private Date createTime;

    public SetUpInfo() {

        super();
    }

    public SetUpInfo(String name, int id, String content, long userId, Date createTime) {

        super();
        this.name = name;
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.createTime = createTime;
    }

    public String getContent() {

        return content;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

}
