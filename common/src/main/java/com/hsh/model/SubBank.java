package com.hsh.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubBank {

	@Id
	private int  id;

	private int  bankId;
	
	private int  cityId;//城市ID
	
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	private String subBankName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getSubBankName() {
		return subBankName;
	}

	public void setSubBankName(String subBankName) {
		this.subBankName = subBankName;
	}
}
