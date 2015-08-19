package com.hsh.dao;

import java.util.List;

import com.hsh.model.FtpContent;

public interface FtpDao {

    FtpContent getContentById(int id);
	
	int saveOrUpdate(FtpContent ftpContent);
	
	boolean isExistMD5(String md5);

	List<FtpContent> getContentById();
	
}
