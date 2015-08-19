package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 用户参与的项目
 * @author linianf
 *
 */
@Entity
public class UserCrowd {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private  int  id;
	
	private  int  userId;//会员ID
	
	private  String mobile;
	
	private  int  itemId;//用户参与的支持项
	
    private  int  crowdId;//按项目查询支持总金额的时候需要，所以做了冗余
	
	private  int  amount;//这个字段查询的比较多，由此在这里做了冗余
	
    private  int  fitPerDay;//做了冗余
	
	private  int  hshQuan;//做了冗余
	
	private  int  quanDays;//惠生活购物卷项目成功几日后发放
	
	@Temporal(TemporalType.TIMESTAMP)
	private  Date buyDate;//购买时间  
	
	private  int  status;//状态 0 已结算  1 进行中   
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public int getQuanDays() {
		return quanDays;
	}

	public void setQuanDays(int quanDays) {
		this.quanDays = quanDays;
	}

	public int getFitPerDay() {
		return fitPerDay;
	}

	public void setFitPerDay(int fitPerDay) {
		this.fitPerDay = fitPerDay;
	}

	public int getHshQuan() {
		return hshQuan;
	}

	public void setHshQuan(int hshQuan) {
		this.hshQuan = hshQuan;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getCrowdId() {
		return crowdId;
	}

	public void setCrowdId(int crowdId) {
		this.crowdId = crowdId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

}
