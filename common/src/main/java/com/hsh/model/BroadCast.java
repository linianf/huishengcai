package com.hsh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 广告
 * 目前广告位的设计有两个点，启动页与首页
 * 每个广告位只允许有一组启用状态的广告
 * @author linianf
 *
 */
@Entity
public class BroadCast {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	private int broadType; // 1首屏广告   2首页轮播广告
	
	private String picURL;
	
	private String picSrc;
	
	private int seq;//轮播中的位置
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date  editDate;
	
	private String  editor;
	
	private int status;// 0停用 1 启用 

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBroadType() {
		return broadType;
	}

	public void setBroadType(int broadType) {
		this.broadType = broadType;
	}

	public String getPicURL() {
		return picURL;
	}

	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	public String getPicSrc() {
		return picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

}
