package com.hsh.util;

public class TradeType {

	public  static  final  int  RECHARGE = 1;//充值
	
	public  static  final  int  TRANSFER = 2;//提现
	
	public  static  final  int  WALLET_INTEREST = 3;//钱包余额产生收益
	
	public  static  final  int  CROWD_INTEREST = 4;//参与众筹金额产生收益  
	
	public  static  final  int  CROWD_PENALTY = 5;//众筹中途退出扣除罚金，包含之前所产生的收益
	
	public  static  final  int  CROWD_OVER = 6;//众筹项目结束返还本金，如果用户退出，扣除罚金从返还的本金中扣除
	
	public  static  final  int  CROWD_DEDUCE = 7;//支持众筹项目，扣除钱包余额
	
}
