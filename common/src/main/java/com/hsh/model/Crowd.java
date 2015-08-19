package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.hsh.util.HSHUtil;

/**
 * 众筹项目
 * @author linianf
 *
 */
@Entity
public class Crowd {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  int  id;
	
	private  String  crowdName;//项目名称
	
	private  String  picURL;//列表页图片地址
	
	@Temporal(TemporalType.TIMESTAMP)
	private  Date  crowdStartDate;
	
	private  int  minRate;
	
	private  int  maxRate;
	
	private  int  orderValue;//排序值，用于后台置顶功能，从redis自增序列获得值
	
	private  int  targetMoney;//目标金额
	
	@Temporal(TemporalType.TIMESTAMP)
	private  Date editDate; //众筹项目的编辑时间
	
	private  String editor;//众筹项目的编辑人
	
	@Temporal(TemporalType.TIMESTAMP)
	private  Date endDate;//根据当前时间及endDate计算项目的剩余天数
	
	private  int  status;//是否已结算  0已结算   1  未结算
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public int getTargetMoney() {
		return targetMoney;
	}

	public void setTargetMoney(int targetMoney) {
		this.targetMoney = targetMoney;
	}
	
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCrowdName() {
		return crowdName;
	}

	public void setCrowdName(String crowdName) {
		this.crowdName = crowdName;
	}

	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	public int getRemainDays() {
		return HSHUtil.calacDaysFrom2Date(new Date(), endDate);
	}

	public Date getCrowdStartDate() {
		return crowdStartDate;
	}

	public void setCrowdStartDate(Date crowdStartDate) {
		this.crowdStartDate = crowdStartDate;
	}

	public int getMinRate() {
		return minRate;
	}

	public void setMinRate(int minRate) {
		this.minRate = minRate;
	}

	public int getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(int maxRate) {
		this.maxRate = maxRate;
	}
	
	public int getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(int orderValue) {
		this.orderValue = orderValue;
	}
}
