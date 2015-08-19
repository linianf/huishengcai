package com.hsh.service;

import java.util.List;

import com.hsh.model.RateConfig;
import com.hsh.model.Wallet;
import com.hsh.model.WalletFlow;

public interface WalletService {

    int saveOrUpdate(Wallet wallet);
	
	int saveOrUpdate(WalletFlow walletFlow);
	
	List<WalletFlow> getWalletFlowByUserId(int userId,int maxFlowId,int pageSize);
	
	int getUserWeekGain(int userId);
	
	int getUserMonthGain(int userId);
	
	int getUserDayGain(int userId);
	
	int getUserTotalGain(int userId);
	
	int getUserTotalEarn();
	
	Wallet getWalletByUserId(int userId);
	
	void  addUserWallet(int userId,int amount, int flowType);
	
	void  duceUserWallet(int userId, int amount, int flowType);
	
	List<Wallet> getWalletList(int lastMinId, int pageSize);
	
	List<RateConfig> getRateConfig();
}
