package com.hsh.service;

import java.util.List;

import com.hsh.model.Recharge;
import com.hsh.model.Transfer;

public interface OrderService {

    long saveOrUpdateRecharge(Recharge recharge);
	
	long saveOrUpdateRecharge(Transfer transfer);
	
	//提现定时任务使用
	List<Transfer> getTransferList(int lastMinId,int pageSize);
	
	Transfer  getTransferById(long id);
	
	Recharge  getRechargeById(long id);
}
