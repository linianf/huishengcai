package com.hsh.dao;

import java.util.List;

import com.hsh.model.Recharge;
import com.hsh.model.Transfer;

public interface OrderDao {
    
	long saveOrUpdateRecharge(Recharge recharge);
	
	long saveOrUpdateRecharge(Transfer transfer);
	
	//提现定时任务使用
	List<Transfer> getTransferList(String fromDate,String toDate,int lastMinId,int pageSize);
	
	Transfer  getTransferById(long id);
	
	Recharge  getRechargeById(long id);
}
