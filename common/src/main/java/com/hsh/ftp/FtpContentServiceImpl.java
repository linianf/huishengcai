package com.hsh.ftp;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hsh.dao.FtpDao;
import com.hsh.model.FtpContent;

@Service("ftpContentService")
@Transactional
public class FtpContentServiceImpl  implements FtpContentService {
	
	static Logger log = LoggerFactory.getLogger(FtpContentServiceImpl.class);

	@Value("${nfs.root.dir}")
	private String ftpRootDir;
	
	@Autowired
	private FtpDao ftpDao;
	                     
	@Override
	public FtpContent store(MultipartFile file) throws Exception {
		//先存储FtpContent
		FtpContent c = new FtpContent();
		c.setType(file.getContentType());
		c.setLength((int)file.getSize());
		c.setMd5(DigestUtils.md5DigestAsHex(file.getBytes()));
		c.setKey(FtpPathEnum.Temp.Path);
		c.setOrigName(file.getOriginalFilename());
		c.setValue(DigestUtils.md5DigestAsHex(file.getBytes())+ "."
				+ FilenameUtils.getExtension(file.getOriginalFilename()));
		ftpDao.saveOrUpdate(c);
		try {
			File f = new File(ftpRootDir + c.getKey() + File.separator
					+ c.getValue());
			if (!f.exists()) {
				FileUtils.forceMkdir(new File(ftpRootDir + c.getKey()));
				FileUtils.touch(f);
				FileUtils.writeByteArrayToFile(f, file.getBytes());
			}
			if (this.validateFile(file, FileSuffixEnum.BMP,
					FileSuffixEnum.JPEG, FileSuffixEnum.JPG,
					FileSuffixEnum.PNG)) {
				PicResizeTask task = new PicResizeTask();
				task.setDirectory(ftpRootDir + c.getKey());
				task.setFileName(c.getValue());
				PicResizeExecutor.doJob(task);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return c;
	}

	@Override
	public final String getFtpRootPath() {
		return this.ftpRootDir;
	}

	@Override
	public String getFtpFilePath(FtpContent fc) {
		if(fc==null)
			return null;
		return ftpRootDir+ fc.getKey() + File.separatorChar + fc.getValue();
	}

	@Override
	public FtpContent getFtpContentById(int id) {
		return ftpDao.getContentById(id);
	}
	
	@Override
	public List<FtpContent> getFtpContent() {
		return ftpDao.getContentById();
	}

	@Override
	public boolean isExistMD5(String md5) {
		return ftpDao.isExistMD5(md5);
	}
	
	/**
	 * 下载网络url apk资源,存储在本地
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@Override
	public FtpContent storeApkFromNetwork(String destUrl,String fileName) throws Exception {
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			HttpURLConnection httpUrl = null;
			URL url = null;
			byte[] buf = new byte[1024];
			int size = 0;
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			File f = new File(ftpRootDir + FtpPathEnum.UC.Path +File.separator+ fileName);
			if (!f.exists()) {
				FileUtils.forceMkdir(new File(ftpRootDir + FtpPathEnum.UC.Path));
				FileUtils.touch(f);
			}
			fos = new FileOutputStream(f);
			while ((size = bis.read(buf)) != -1)
			fos.write(buf, 0, size);
			fos.close();
			bis.close();
			httpUrl.disconnect();

			//先存储FtpContent
			FtpContent c = new FtpContent();
			c.setType("application/x-zip-compressed");
			c.setLength((int)f.length());
			c.setKey(FtpPathEnum.UC.Path);
			c.setOrigName(fileName);
			c.setValue(fileName);
			ftpDao.saveOrUpdate(c);
			return c;
	} 
	
	/**
	 * 下载网络url apk资源,存储在本地
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@Override
	public FtpContent storeJPGFromNetwork(String destUrl,String fileName) throws Exception {
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			HttpURLConnection httpUrl = null;
			URL url = null;
			byte[] buf = new byte[1024];
			int size = 0;
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			File f = new File(ftpRootDir + FtpPathEnum.UC.Path +File.separator+ fileName);
			if (!f.exists()) {
				FileUtils.forceMkdir(new File(ftpRootDir + FtpPathEnum.UC.Path));
				FileUtils.touch(f);
			}
			fos = new FileOutputStream(f);
			while ((size = bis.read(buf)) != -1)
			fos.write(buf, 0, size);
			fos.close();
			bis.close();
			httpUrl.disconnect();

			//先存储FtpContent
			FtpContent c = new FtpContent();
			c.setType("image/jpeg");
			c.setLength((int)f.length());
			c.setKey(FtpPathEnum.UC.Path);
			c.setOrigName(fileName);
			c.setValue(fileName);
			ftpDao.saveOrUpdate(c);
			PicResizeTask task = new PicResizeTask();
			task.setDirectory(ftpRootDir + c.getKey());
			task.setFileName(c.getValue());
			PicResizeExecutor.doJob(task);
			return c;
	} 
	
	
	public static void teststoreApkFromNetwork(String destUrl,String fileName) throws Exception {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[1024];
		int size = 0;
		url = new URL(destUrl);
		httpUrl = (HttpURLConnection) url.openConnection();
		httpUrl.connect();
		bis = new BufferedInputStream(httpUrl.getInputStream());
		File f = new File("C:\\file\\" +  fileName);
		if (!f.exists()) {
			FileUtils.touch(f);
		}
		fos = new FileOutputStream(f);
		while ((size = bis.read(buf)) != -1)
		fos.write(buf, 0, size);
		fos.close();
		bis.close();
		httpUrl.disconnect();
	}
	
	public boolean validateFile(MultipartFile file, FileSuffixEnum... enums) {
		try {
			if (file == null || file.isEmpty()) {
				return false;
			}
			for (FileSuffixEnum t : enums) {
				if (StringUtils
						.equals(t.name().toUpperCase(), FilenameUtils
								.getExtension(file.getOriginalFilename())
								.toUpperCase())) {
					return true;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		FtpContentServiceImpl.teststoreApkFromNetwork("http://down4.game.uc.cn/wm/0/16/Sanguo_AndroidAuto_New_Large_CCCCACCE_247248_153747bb4957.apk?sh=10", "b.apk");
	}
}
