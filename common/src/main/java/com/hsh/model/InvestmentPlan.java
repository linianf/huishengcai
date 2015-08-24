package com.hsh.model;

import java.util.Date;

/**
 * 投资方案
 * 
 * @author lengxiangwu
 * 
 */
public class InvestmentPlan extends ABaseEntity {

    private static final long serialVersionUID = 3271195140486180243L;

    private long id;

    // 项目id
    private long projectId;

    // 名称
    private String name;

    // 日收益率
    private double dailyRate;

    // 投资金额
    private long amount;

    // 创建人id
    private long userId;

    // 创建时间
    private Date createTime;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public long getProjectId() {

        return projectId;
    }

    public void setProjectId(long projectId) {

        this.projectId = projectId;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getDailyRate() {

        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {

        this.dailyRate = dailyRate;
    }

    public long getAmount() {

        return amount;
    }

    public void setAmount(long amount) {

        this.amount = amount;
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
