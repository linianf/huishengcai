package com.hsh.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Entity;

@Entity
public class PushLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 创建人
    private long userId;

    // 推送时间
    private Date sendTime;

    // 推送内容
    private String content;

    public PushLog() {

        super();
    }

    public PushLog(long userId, Date sendTime, String content) {

        super();
        this.userId = userId;
        this.sendTime = sendTime;
        this.content = content;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }

    public Date getSendTime() {

        return sendTime;
    }

    public void setSendTime(Date sendTime) {

        this.sendTime = sendTime;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

}
