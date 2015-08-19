package com.hsh.quartz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsh.model.RateConfig;
import com.hsh.model.UserCrowd;
import com.hsh.model.Wallet;
import com.hsh.service.UserCrowdService;
import com.hsh.service.WalletService;
import com.hsh.util.TradeType;

/**
 * 每日3时开始执行.计算用户今日利息,并发放至用户钱包余额
 * @author linianf
 *
 */
public class GainPerDayTask {

	private static Logger log = LoggerFactory.getLogger(GainPerDayTask.class);
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private UserCrowdService userCrowdService;
	
	private static List<RateConfig> configList = null;
	
	public void gainPerDay(){
		log.info("开始执行每日收益任务...........");
		int lastMinId = Integer.MAX_VALUE;
		List<Wallet> walletList = walletService.getWalletList(0, 100);
		int walletCount = 0;
		int crowdCount = 0;
		//计算钱包余额产生的收益,并发放
		if(walletList!=null){
			while(true){
				for(Wallet wallet:walletList){
					if(lastMinId > wallet.getId()){
						lastMinId = wallet.getId();
					}
					int balance = wallet.getBalance();
					//钱包每日收益
					int walletGain = caculWalletGain(balance);
					if(walletGain > 0){
					   walletCount++;
					   walletService.addUserWallet(wallet.getUserId(), walletGain, TradeType.WALLET_INTEREST);
					}
				}
				if(walletList.size()<100){
					break;
				}else{
					walletList = walletService.getWalletList(lastMinId, 100);
				}
			}
		}
		log.info("共执行钱包收益（钱包余额太低无收益的不计算）入账笔数:" + walletCount);
		//计算用户支持的项目,并根据项目支持项定义的每日收益进行发放
		List<UserCrowd> crowdList = userCrowdService.getInCrowdList(100,0);
		lastMinId = Integer.MAX_VALUE;
		if(crowdList!=null){
			while(true){
				for(UserCrowd userCrowd:crowdList){
					if(lastMinId > userCrowd.getId()){
						lastMinId = userCrowd.getId();
					}
					int crowdGain = userCrowd.getFitPerDay();
					crowdCount++;
					walletService.addUserWallet(userCrowd.getUserId(), crowdGain, TradeType.CROWD_INTEREST);
				}
				if(crowdList.size()<100){
					break;
				}else{
					crowdList = userCrowdService.getInCrowdList(100,lastMinId);
				}
			}
		}
		log.info("共执行项目支持收益入账笔数:" + crowdCount);
	}
	
	/**
	 * 根据钱包余额按照指定的规则计算用户每日收益
	 * @param balance
	 * @return
	 */
	public int caculWalletGain(int balance){
		if(configList==null){
			configList = walletService.getRateConfig();
		}
		for(RateConfig config:configList){
			if(balance >= config.getMinValue() && balance < config.getMaxValue()){
				return (int)(balance*config.getRatePerDay());
			}
		}
		return 0;
	}
}
