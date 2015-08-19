package com.hsh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author linianf
 *
 */

@Entity
public class Wallet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  int  id;
	
	private  int  userId;
	
	private  int  balance;//余额（单位分）
	
	private  int  vouchers;//抵用卷
	
	private  int  crowds;//众筹保证金   

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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getVouchers() {
		return vouchers;
	}

	public void setVouchers(int vouchers) {
		this.vouchers = vouchers;
	}

	public int getCrowds() {
		return crowds;
	}

	public void setCrowds(int crowds) {
		this.crowds = crowds;
	}
	
}
