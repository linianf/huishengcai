package com.hsh.ftp;


/**
 * FtpPathEnum.java
 */
public enum FtpPathEnum {
	Temp("/md5/"), BatchExcel("/batch/"),UC("/uc/");
	public String Path;

	private FtpPathEnum(String path) {
		this.Path = path;
	}
}
