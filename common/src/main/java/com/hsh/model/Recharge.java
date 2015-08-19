package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 充值
 * @author linianf
 *
 */
@Entity
public class Recharge {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  long  id;
	private long userId;// 充值的人
	private int amount;//充值金额
	private int state;//充值状态    0 未发送连连支付  1 连连支付处理中  2充值已完成  3充值失败
	private Date paySuccessTime;//支付成功后，第三方支付成功的时间
	private int bankCardId;//用户所使用的银行卡ID
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(int bankCardId) {
		this.bankCardId = bankCardId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getPaySuccessTime() {
		return paySuccessTime;
	}
	public void setPaySuccessTime(Date paySuccessTime) {
		this.paySuccessTime = paySuccessTime;
	}
	      
}
