package com.hsh.vo;

public class QuanVO {

	private int amount;
	
	private int userId;
	
	private int crowdId;
	
	private int itemId;
	
	private String mobile;
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
}
