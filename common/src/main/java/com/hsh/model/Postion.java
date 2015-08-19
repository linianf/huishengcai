package com.hsh.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 位置实体
 * @author lengxiangwu
 *
 */
@Entity
public class Postion {
	//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	// 位置名称
	private String name;
	// 位置备注
	private String remark;
	// 创建时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	// 创建人
	private int userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
