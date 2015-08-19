package com.hsh.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 钱包流水中的交易类型定义
 * 
 * @author linianf
 *
 */
@Entity
public class TradeCode {

	@Id
	private int code;
	
	private String name;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
