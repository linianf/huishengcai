package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;

/**
 * 钱包余额日利率配置表
 * 要求大于等于最小值,小于最大值
 * @author linianf
 *
 */
@Entity
public class RateConfig {

	private int id;
	
	private String desc;
	
	private int maxValue;
	
	private int minValue;
	
	private int userId;

    private double ratePerDay;
    
    private Date createTime;
	
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getRatePerDay() {
		return ratePerDay;
	}

	public void setRatePerDay(double ratePerDay) {
		this.ratePerDay = ratePerDay;
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
