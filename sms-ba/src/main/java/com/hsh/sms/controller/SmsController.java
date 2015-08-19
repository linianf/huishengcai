package com.hsh.sms.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hsh.util.Constants;
import com.hsh.util.RedisUtil;
import com.hsh.vo.SmsVO;

@Controller
@RequestMapping("/hsh/sms")
public class SmsController {

	@Autowired
	private RedisUtil redisUtil;
	
	private static Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(177)|(18[0-9]))\\d{8}$");  

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("user") != null) {
			return "sms.ba";
		}
		return "sms.login";
	}

	@RequestMapping(value = "/login")
	public  String  login(HttpServletRequest request,String username,String password,Model model){
		
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("user") != null) {
			return "sms.ba";
		}
		
		if(username!=null&&password!=null){
			//检查ip地址
			String ip = getIpAddr(request);
			if(!ip.equals("222.187.104.14")){
				model.addAttribute("error", "您的IP地址不允许登陆");
				return "sms.login";
			}
			if(!"admin".equals(username)){
				model.addAttribute("error", "用户名不存在");
				return "sms.login";
			}
			if(!"admin123".equals(password)){
				model.addAttribute("error", "密码不正确");
				return "sms.login";
			}
			session.setAttribute("user", "1");
			return "sms.ba";
		}
		return "sms.login";
	}
	
	@RequestMapping(value = "/sendSms", method = RequestMethod.POST)
	public  String  sendSms(HttpServletRequest request,String mobile,String content,Model model){
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("user") == null) {
			return "sms.login";
		}
		if(StringUtils.isEmpty(mobile)){
			model.addAttribute("error", "请输入手机号");
			return "sms.ba";
		}
		
		if(StringUtils.isEmpty(content)){
			model.addAttribute("error", "请输入待发短信的内容");
			return "sms.ba";
		}
		String[] tels = null;
		SmsVO vo = new SmsVO();
		vo.setContent(content);
		if(mobile.contains(":")){
			 tels = mobile.split(":");
		}else{
			 tels = new String[]{mobile}; 
		}
		for(String tel:tels){
			Matcher m = p.matcher(tel);  
			if(!m.matches()){
				model.addAttribute("error", "请输入合法的手机号");
				return "sms.ba";
			}
		}
		vo.setMobile(tels);
		vo.setBusiType(1);
		redisUtil.lpush(Constants.SMS_QUEUE,JSON.toJSONString(vo));  
		model.addAttribute("error", "短信已发送成功！");
		return "sms.ba";
	}
	
	@RequestMapping(value = "/sendHshSms", method = RequestMethod.POST)
	@ResponseBody
	public  String  sendHshSms(HttpServletRequest request,String mobile,String content){
		if(StringUtils.isEmpty(mobile)){
			return "请输入手机号";
		}
		
		if(StringUtils.isEmpty(content)){
			return "请输入待发短信的内容";
		}
		
		//检查ip地址
		String ip = getIpAddr(request);
		if(!ip.equals("121.40.142.152")&&!ip.equals("121.40.142.176")
				&&!ip.equals("121.40.189.250")&&!ip.equals("121.40.189.25")
				&&!ip.equals("221.226.34.186")){
			return "您的IP地址不允许";
		}
		
		String[] tels = null;
		SmsVO vo = new SmsVO();
		vo.setContent(content);
		if(mobile.contains(":")){
			 tels = mobile.split(":");
		}else{
			 tels = new String[]{mobile}; 
		}
		for(String tel:tels){
			Matcher m = p.matcher(tel);  
			if(!m.matches()){
				return "请输入合法的手机号";
			}
		}
		vo.setMobile(tels);
		vo.setBusiType(3);
		redisUtil.lpush(Constants.SMS_QUEUE,JSON.toJSONString(vo));  
		return "ok";
	}
	
	@RequestMapping(value = "/sendHscSms", method = RequestMethod.POST)
	@ResponseBody
	public  String  sendHscSms(HttpServletRequest request,String mobile,String content,Model model){
		if(StringUtils.isEmpty(mobile)){
			return "请输入手机号";
		}
		
		if(StringUtils.isEmpty(content)){
			return "请输入待发短信的内容";
		}
		String[] tels = new String[1];
		SmsVO vo = new SmsVO();
		vo.setContent(content);
		Matcher m = p.matcher(mobile);  
		if(!m.matches()){
			return "请输入合法的手机号";
		}
		tels[0] = mobile;
		vo.setMobile(tels);
		vo.setBusiType(2);
		redisUtil.lpush(Constants.SMS_QUEUE,JSON.toJSONString(vo));  
		return "ok";
	}
	

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
