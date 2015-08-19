package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class QuanGetRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private   long  id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private   Date  callDate;
	
	private   String  quanNO;
	
	private   int    userId;
	
	private   String    mobile;
	
	private   int    crowdId;
	
	private   int    itemId;
	
	public int getCrowdId() {
		return crowdId;
	}

	public void setCrowdId(int crowdId) {
		this.crowdId = crowdId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	private   int  amount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public String getQuanNO() {
		return quanNO;
	}

	public void setQuanNO(String quanNO) {
		this.quanNO = quanNO;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
