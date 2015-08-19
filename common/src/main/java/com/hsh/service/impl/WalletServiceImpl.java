package com.hsh.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsh.dao.WalletDao;
import com.hsh.model.RateConfig;
import com.hsh.model.Wallet;
import com.hsh.model.WalletFlow;
import com.hsh.service.WalletService;

@Service("walletService")
@Transactional(readOnly=true)
public class WalletServiceImpl implements WalletService{

	@Autowired
	private WalletDao walletDao;
	
	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdate(Wallet wallet) {
		return walletDao.saveOrUpdate(wallet);
	}

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdate(WalletFlow walletFlow) {
		return walletDao.saveOrUpdate(walletFlow);
	}

	@Override
	public List<WalletFlow> getWalletFlowByUserId(int userId, int lastMinFlowId,
			int pageSize) {
		return walletDao.getWalletFlowByUserId(userId, lastMinFlowId, pageSize);
	}

	@Override
	public int getUserWeekGain(int userId) {
		return walletDao.getUserWeekGain(userId);
	}

	@Override
	public int getUserMonthGain(int userId) {
		return walletDao.getUserMonthGain(userId);
	}

	@Override
	public int getUserDayGain(int userId) {
		return walletDao.getUserDayGain(userId);
	}

	@Override
	public int getUserTotalGain(int userId) {
		return walletDao.getUserTotalGain(userId);
	}

	@Override
	@Transactional(readOnly=false)
	public void addUserWallet(int userId, int amount, int flowType) {
		walletDao.addUserWallet(userId, amount);
		//增加流水
		WalletFlow flow = new WalletFlow();
		flow.setAmount(amount);
        flow.setInOut(1);
        flow.setOperateDate(new Date());
        flow.setUserId(userId);
        flow.setTradeType(flowType);
        walletDao.saveOrUpdate(flow);
	}

	@Override
	@Transactional(readOnly=false)
	public void duceUserWallet(int userId, int amount, int flowType) {
		walletDao.addUserWallet(userId, amount);
		//增加流水
		WalletFlow flow = new WalletFlow();
		flow.setAmount(amount);
        flow.setInOut(1);
        flow.setOperateDate(new Date());
        flow.setUserId(userId);
        flow.setTradeType(flowType);
        walletDao.saveOrUpdate(flow);
	}
	
	@Override
	public List<Wallet> getWalletList(int lastMinId, int pageSize) {
		return walletDao.getWalletList(lastMinId, pageSize);
	}

	@Override
	public List<RateConfig> getRateConfig() {
		return walletDao.getRateConfig();
	}

	@Override
	public Wallet getWalletByUserId(int userId) {
		return walletDao.getWalletByUserId(userId);
	}

	@Override
	public int getUserTotalEarn() {
		return walletDao.getUserTotalEarn();
	}

}
