package com.hsh.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hsh.service.UserService;
import com.hsh.service.WalletService;
import com.hsh.util.Constants;
import com.hsh.util.HSHUtil;
import com.hsh.util.RedisUtil;
import com.hsh.vo.AjaxResult;
import com.hsh.vo.SmsVO;

@Controller
@RequestMapping("/sms")
public class VerifyCodeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 请求短信验证码接口
	 * 同一业务场景60秒内不得重复申请发送短信
	 * @param mobile
	 * @param scene
	 * @return
	 */
	@RequestMapping(value = "/captcha")
	@ResponseBody
	public  AjaxResult  captcha(String mobile,int scene){
		String serial = mobile+"_"+scene;
		//从redis查询验证码
		String captcha = redisUtil.get(serial);
		if(StringUtils.isEmpty(captcha)){
			captcha = HSHUtil.genVarifyCode();
		}
		else{
			return AjaxResult.failed("同一业务场景60秒内不允许重复发送验证码短信");
		}
		redisUtil.setex(serial,90,captcha);
		//下发验证码短信
		SmsVO vo = new SmsVO();
		vo.setContent("您本次操作的验证码为：" +captcha);
		vo.setMobile(new String[]{mobile});
		vo.setBusiType(2);
		redisUtil.lpush(Constants.SMS_QUEUE,JSON.toJSONString(vo));  
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("serial", serial);
		return AjaxResult.success(obj);
	}
	
	/**
	 * 验证码校验接口
	 * @param serial
	 * @param captcha
	 * @return
	 */
	@RequestMapping(value = "/captcha/valid")
	@ResponseBody
	public  AjaxResult  captchaValid(String serial, String captcha){
		String realCaptcha = redisUtil.get(serial);
		if(StringUtils.isEmpty(realCaptcha)){
			return AjaxResult.failed("验证码已过期，请重新获取！");
		}
		if(StringUtils.isEmpty(captcha)){
			return AjaxResult.failed("请输入验证码");
		}
		if(!captcha.equals(realCaptcha)){
			return AjaxResult.failed("验证码不正确");
		}
		Map<String,Object> obj = new HashMap<String,Object>();
		return AjaxResult.success(obj);
	}
}
