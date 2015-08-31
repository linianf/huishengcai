package com.hsh.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Entity;

/**
 * 
 * @author lengxiangwu
 * 
 */
@Entity
public class Project extends ABaseEntity {

    private static final long serialVersionUID = 7268685961118560736L;

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 项目名称
    private String name;

    // 预期年华收益率
    private String expectedYields;

    // 募资金额
    private String fundraisingAmount;

    // 项目简介
    private String summary;

    // 开始时间
    private Date startTime;

    // 是否推荐
    private int recommended;

    // 项目状态
    private int state;

    // 持续天数
    private int lastDays;

    // 罚金
    private long forfeit;

    // 图片地址
    private String imgUrl;

    private int userId;

    private Date createTime;

    public static final int RECOMMENDED_YES = 1;

    public static final int RECOMMENDED_NO = 0;

    public static final int STATE_ONLINE = 1;

    public static final int STATE_NOT_ONLINE = 0;

    public static final int STATE_OFFLINE = 2;

    public static final Map <Integer, String> getAllState() {

        Map <Integer, String> stateList = new HashMap <Integer, String>();
        stateList.put(STATE_ONLINE, "上线");
        stateList.put(STATE_NOT_ONLINE, "未上线");
        stateList.put(STATE_OFFLINE, "已下线");
        return stateList;
    }

    public String getStateString() {

        if (this.state == STATE_NOT_ONLINE) {
            return "未上线";
        } else if (this.state == STATE_OFFLINE) {
            return "已下线";
        } else {
            return "上线";
        }
    }

    public boolean isRecommended() {

        return this.recommended == RECOMMENDED_YES;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getExpectedYields() {

        return expectedYields;
    }

    public void setExpectedYields(String expectedYields) {

        this.expectedYields = expectedYields;
    }

    public String getFundraisingAmount() {

        return fundraisingAmount;
    }

    public void setFundraisingAmount(String fundraisingAmount) {

        this.fundraisingAmount = fundraisingAmount;
    }

    public String getSummary() {

        return summary;
    }

    public void setSummary(String summary) {

        this.summary = summary;
    }

    public Date getStartTime() {

        return startTime;
    }

    public void setStartTime(Date startTime) {

        this.startTime = startTime;
    }

    public int getLastDays() {

        return lastDays;
    }

    public void setLastDays(int lastDays) {

        this.lastDays = lastDays;
    }

    public long getForfeit() {

        return forfeit;
    }

    public void setForfeit(long forfeit) {

        this.forfeit = forfeit;
    }

    public int getRecommended() {

        return recommended;
    }

    public void setRecommended(int recommended) {

        this.recommended = recommended;
    }

    public int getState() {

        return state;
    }

    public void setState(int state) {

        this.state = state;
    }

    public String getImgUrl() {

        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {

        this.imgUrl = imgUrl;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public Date getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = createTime;
    }
}
