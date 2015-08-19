package com.hsh.app.controller;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hsh.ftp.FtpContentService;
import com.hsh.model.FtpContent;

/**
 * 
 * @author linianf
 *
 */

@Controller
@RequestMapping("/static")
public class StaticController {
	
	static Logger log = LoggerFactory.getLogger(StaticController.class);
	
	@Value("${nfs.root.dir}")
	private String ftpRootDir;
	
	@Autowired
	protected FtpContentService ftpContentService;

	/**
	 * 请求指定图片，返回缩放成指定尺寸的文件
	 * @param id
	 * @param w
	 * @param h
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/source/{id}", method = RequestMethod.GET)
	public void load(@PathVariable int id, @RequestParam(value = "w", required = false) Integer w,@RequestParam(value = "t", required = false) Integer t,
			HttpServletRequest req, HttpServletResponse resp) {
		FtpContent f = ftpContentService.getFtpContentById(id);
		if (f == null) {
			return;
		}
		InputStream is = null;
		OutputStream os = null;
		File file = null;
		try {
			if(w!=null&&t==null){
				String value = f.getValue();
				file = new File(ftpRootDir + f.getKey()
						+ File.separator + value.substring(0,value.lastIndexOf("."))+"_"+w.intValue()+".jpg");
				is = FileUtils.openInputStream(file);
			}else{
				is = FileUtils.openInputStream(new File(ftpRootDir + f.getKey()
						+ File.separator + f.getValue()));
			}
			if(w!=null){
				resp.setContentType("image/jpeg");
				resp.setContentLength((int)file.length());
			}else{
				if (StringUtils.isNotBlank(f.getType())){
					resp.setContentType(f.getType());
				}
				resp.setContentLength(f.getLength());
			}
			
			if(t!=null){
				String contentDisposition = "attachment; filename=" + f.getOrigName();
				resp.setHeader("Content-Disposition", contentDisposition);
			}
			os = resp.getOutputStream();
			IOUtils.copy(is, os);
			os.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
}
