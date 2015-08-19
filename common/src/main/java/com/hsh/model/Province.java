package com.hsh.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Province {

	@Id
	private int id;
	
	private String province;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
