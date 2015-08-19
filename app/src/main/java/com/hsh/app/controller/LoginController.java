package com.hsh.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsh.model.HSCUser;
import com.hsh.model.Wallet;
import com.hsh.service.UserService;
import com.hsh.service.WalletService;
import com.hsh.util.RedisUtil;
import com.hsh.vo.AjaxResult;

@Controller
@RequestMapping("/user")
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private static Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(177)|(18[0-9]))\\d{8}$"); 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 用户登录
	 * @param username
	 * @param password md5后的密码
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public  AjaxResult login(String mobile, String pwd){
		log.info("user:" + mobile + " logging");
		if(StringUtils.isEmpty(mobile)){
			return AjaxResult.failed("手机号不允许为空");
		}
		if(StringUtils.isEmpty(pwd)){
			return AjaxResult.failed("密码不允许为空");
		}
		Matcher m = p.matcher(mobile);  
		if(!m.matches()){
			return AjaxResult.failed("请输入合法的手机号");
		}
		HSCUser user = userService.getUserById(mobile);
		String realPass = user.getLoginPass();
		if(!pwd.equals(realPass)){
			return AjaxResult.failed("密码不正确");
		}
		Wallet wallet = walletService.getWalletByUserId(user.getId());
		int totalGain = walletService.getUserTotalGain(user.getId());
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("userId", user.getId());
		obj.put("mobile", mobile);
		obj.put("balance", wallet.getBalance());
		obj.put("vouchers", wallet.getVouchers());
		obj.put("crowds", wallet.getCrowds());
		obj.put("totalGain", totalGain);
		return AjaxResult.success(obj);
	}
	
	/**
	 * 用户注册
	 * @param mobile
	 * @param serial
	 * @param captcha
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public  AjaxResult register(String mobile, String serial,String captcha,String pwd){
		String realCaptcha = redisUtil.get(serial);
		if(StringUtils.isEmpty(realCaptcha)){
			return AjaxResult.failed("验证码已过期");
		}
		log.info("user:" + mobile + " logging");
		if(StringUtils.isEmpty(mobile)){
			return AjaxResult.failed("手机号不允许为空");
		}
		if(StringUtils.isEmpty(pwd)){
			return AjaxResult.failed("密码不允许为空");
		}
		if(StringUtils.isEmpty(captcha)){
			return AjaxResult.failed("验证码不允许为空");
		}
		Matcher m = p.matcher(mobile);  
		if(!m.matches()){
			return AjaxResult.failed("请输入合法的手机号");
		}
		if(!captcha.equals(realCaptcha)){
			return AjaxResult.failed("验证码不正确");
		}
		//用户注册
		int userId = userService.userRegister(mobile, pwd);
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("userId", userId);
		obj.put("mobile", mobile);
		return AjaxResult.success(obj);
	}
}
