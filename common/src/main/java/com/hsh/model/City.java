package com.hsh.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {

	@Id
	private int id;
	
	private int  provinceId;
	
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
