package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 钱包流水
 * @author linianf
 *
 */
@Entity
public class WalletFlow {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private  int  id;
	
	private  int  userId;
	
	private  int  tradeType;
	
	private  int  amount;
	
	private  int  inOut; //0表示 扣钱   1 表示收益  
	
	@Temporal(TemporalType.TIMESTAMP)
	private  Date  operateDate;
	
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

	public int getTradeType() {
		return tradeType;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getInOut() {
		return inOut;
	}

	public void setInOut(int inOut) {
		this.inOut = inOut;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

}
