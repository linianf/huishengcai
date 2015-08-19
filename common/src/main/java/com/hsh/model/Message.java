package com.hsh.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Entity;

@Entity
public class Message {
    // 主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    // 消息标题
    private String title;

    // 消息内容
    private String content;

    // 推送类型
    private int type;

    // 立即发送
    public static final int TYPE_IMMEDIATELY = 0;

    // 定时发送
    public static final int TYPE_TIMING = 1;

    // 推送时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date pushTime;

    // 创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    // 状态
    private int state;

    // 未发送
    public static final int STATE_NOT_SENT = 0;

    // 已发送
    public static final int STATE_SENT = 1;

    // 创建人
    private long userId;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public Date getPushTime() {

        return pushTime;
    }

    public void setPushTime(Date pushTime) {

        this.pushTime = pushTime;
    }

    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {

        this.userId = userId;
    }
    
    public boolean isImmediately(){
        return type == TYPE_IMMEDIATELY;
    }

}
