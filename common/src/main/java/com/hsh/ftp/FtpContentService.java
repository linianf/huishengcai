package com.hsh.ftp;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hsh.model.FtpContent;


public interface FtpContentService {
	
	String getFtpRootPath();
	
	String getFtpFilePath(FtpContent f);

	FtpContent store(MultipartFile file) throws Exception;
	
	FtpContent getFtpContentById(int id);
	
	boolean isExistMD5(String md5);
	
	FtpContent storeApkFromNetwork(String destUrl,String fileName) throws Exception;
	
	FtpContent storeJPGFromNetwork(String destUrl,String fileName) throws Exception;

	List<FtpContent> getFtpContent();
}
