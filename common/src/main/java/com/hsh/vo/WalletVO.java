package com.hsh.vo;

public class WalletVO {

	private  int  gainsToday;//今日利息
	
	private  int  balance;//钱包余额
	
	private  int  rateYear;//年化收益
	
	public int getGainsToday() {
		return gainsToday;
	}

	public void setGainsToday(int gainsToday) {
		this.gainsToday = gainsToday;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getRateYear() {
		return rateYear;
	}

	public void setRateYear(int rateYear) {
		this.rateYear = rateYear;
	}

}
