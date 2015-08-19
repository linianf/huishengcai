package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 众筹的支持项目定义
 * @author linianf
 *
 */
@Entity
public class CrowdItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  int  id;
	
	private  int  crowdId;//众筹ID
	
	private  int  amount;//支持金额
	
	private  int  fitPerDay;//每日利息
	
	private  int  state;//1.启用   2.停用
	
	private  int  hshQuan;//项目成功后的 惠生活购物卷回报的数量 
	
	private  int  quanDays;//惠生活购物卷项目成功几日后发放
	
	@Temporal(TemporalType.TIMESTAMP)
	private  Date editDate;
	
	private  String editor;
	
	private  int  penalty;//用户主动退出，罚金
	
	public int getQuanDays() {
		return quanDays;
	}

	public void setQuanDays(int quanDays) {
		this.quanDays = quanDays;
	}

	
	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getHshQuan() {
		return hshQuan;
	}

	public void setHshQuan(int hshQuan) {
		this.hshQuan = hshQuan;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getFitPerDay() {
		return fitPerDay;
	}

	public void setFitPerDay(int fitPerDay) {
		this.fitPerDay = fitPerDay;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCrowdId() {
		return crowdId;
	}

	public void setCrowdId(int crowdId) {
		this.crowdId = crowdId;
	}
}
