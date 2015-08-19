package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 提现
 * @author linianf
 *
 */
@Entity
public class Transfer {

    public static final int STATUS_BANK = 1;// 银行处理中
	
	public static final int STATUS_FAILED = 2;// 转账失败
	
	public static final int STATUS_HAND = 3;//生僻字手动汇款

	public static final int MIN_TRANSFER  =  2000;// 银行卡普通提现最低限额(钱宝)
	
	public static final int MAX_TRANSFER  = 100000;// 快速提现最低金额(钱宝)
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  long  id;  
	
	private  int   userId;
	 
	private  int   amount;
	
	private String province;

	private String city;

	private String bank;

	private String subBank;

	private String cardNum;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;//订单创建时间

	@Temporal(TemporalType.TIMESTAMP)
	private Date acceptTime;//银行受理时间

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getSubBank() {
		return subBank;
	}

	public void setSubBank(String subBank) {
		this.subBank = subBank;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
