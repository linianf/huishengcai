package com.hsh.dao;

import java.util.List;

import com.hsh.model.RateConfig;
import com.hsh.model.Wallet;
import com.hsh.model.WalletFlow;

public interface WalletDao {

	int saveOrUpdate(Wallet wallet);
	
	int saveOrUpdate(WalletFlow walletFlow);
	
	List<WalletFlow> getWalletFlowByUserId(int userId,int maxFlowId,int pageSize);
	
	int getUserWeekGain(int userId);
	
	int getUserMonthGain(int userId);
	
	int getUserDayGain(int userId);
	
	int getUserTotalGain(int userId);
	
	void  addUserWallet(int userId,int amount);
	
	List<Wallet> getWalletList(int lastMinId,int pageSize);
	
	List<RateConfig> getRateConfig();
	
	Wallet getWalletByUserId(int userId);
	
}
