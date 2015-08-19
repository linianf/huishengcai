package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 惠生财用户
 * 要求客户端打包时绑定渠道参数，渠道值待定，用于追踪app在个渠道的推广情况
 * @author linianf
 *
 */

@Entity
public class HSCUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id;//会员ID
	
	private String mobile;//手机号
	
	private String loginPass;//登陆密码
	
	private String tradePass;//交易密码
	
	private String referCode;//专属推荐码
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;//注册日期
	
	private int channel;//来源渠道   默认为0
	
	private String realName;//真实姓名
	
	private String identyNum;//身份证号
	
	private int recommendUserId;//我的推荐人ID
	
	public int getRecommendUserId() {
		return recommendUserId;
	}

	public void setRecommendUserId(int recommendUserId) {
		this.recommendUserId = recommendUserId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentyNum() {
		return identyNum;
	}

	public void setIdentyNum(String identyNum) {
		this.identyNum = identyNum;
	}

	
	public String getReferCode() {
		return referCode;
	}

	public void setReferCode(String referCode) {
		this.referCode = referCode;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getTradePass() {
		return tradePass;
	}

	public void setTradePass(String tradePass) {
		this.tradePass = tradePass;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

}
