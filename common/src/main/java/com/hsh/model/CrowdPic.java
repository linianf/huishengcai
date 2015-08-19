package com.hsh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 众筹项目的轮播图片
 * @author linianf
 *
 */
@Entity
public class CrowdPic {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  int id;
	
    private String url;
    
    private int crowdId; 
    
    private int seq;//轮播中的位置
	
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCrowdId() {
		return crowdId;
	}

	public void setCrowdId(int crowdId) {
		this.crowdId = crowdId;
	}

}
