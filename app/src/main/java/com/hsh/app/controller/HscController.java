package com.hsh.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsh.model.BroadCast;
import com.hsh.service.BroadCastService;
import com.hsh.service.UserService;
import com.hsh.service.WalletService;
import com.hsh.vo.AjaxResult;

@Controller
@RequestMapping("/hsc")
public class HscController {

	@Autowired
	private BroadCastService broadCastService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WalletService walletService;
	
	/**
	 * 惠生财首页
	 * @return
	 */
	@RequestMapping(value = "/index")
	@ResponseBody
	public  AjaxResult  index(){
		//获取首页广告
		List<BroadCast> list = broadCastService.getIndexBroadCast();
		//获取平台总用户数
		int totalUser = userService.getTotalUser();
		//获取用户平台总收益
		int totalEarn = walletService.getUserTotalEarn();
		//获取火热推荐项目
		
		return null;
	}
}
