package com.hsh.vo;

public class SmsVO {

	private String[] mobile;//接收手机号
	
	private String content;//内容
	
	private int busiType;//业务类型
	
	public int getBusiType() {
		return busiType;
	}

	public void setBusiType(int busiType) {
		this.busiType = busiType;
	}

	public String[] getMobile() {
		return mobile;
	}

	public void setMobile(String[] mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
