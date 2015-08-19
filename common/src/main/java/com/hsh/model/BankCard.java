package com.hsh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户银行卡信息
 * @author linianf
 *
 */
@Entity
public class BankCard {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int userId;//用户ID
	
	private String cardNum;//卡号
	
	private int cityId;//省份城市
	
	private int subBankId;//开户行ID
	
	private int state;//0 信用卡  1 借记卡
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getSubBankId() {
		return subBankId;
	}

	public void setSubBankId(int subBankId) {
		this.subBankId = subBankId;
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

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}


}
