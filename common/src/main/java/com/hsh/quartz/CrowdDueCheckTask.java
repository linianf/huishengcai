package com.hsh.quartz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsh.model.Crowd;
import com.hsh.model.UserCrowd;
import com.hsh.service.CrowdService;
import com.hsh.service.UserCrowdService;

/**
 * 每日凌晨0时执行，检查于昨日到期的项目，对参与支持资金本金退还至钱包，并发放惠生活充值卷
 * 惠生活充值卷发放时间如果，项目成功时，用户投资已满指定天数，则立刻发放，如果不满指定天数，则按
 * 实际支持天数按比例获得部分金额的购物卷
 * @author linianf
 *
 */
public class CrowdDueCheckTask {
	
    @Autowired
    private CrowdService crowdService;
    
    @Autowired
    private UserCrowdService userCrowdService;

	private static Logger log = LoggerFactory.getLogger(CrowdDueCheckTask.class);
	
	public void crowdDueCheck(){
		log.info("开始检查是否有于昨日到期的项目");
		int lastMinId = Integer.MAX_VALUE;
		int crowdCount = 0;
		int userCrowdCount = 0;
		//所有到期未结算的项目
		List<Crowd> crowdList = crowdService.getAllCrowdDue();
		for(Crowd crowd:crowdList){
			crowdCount++;
			int totalAmount = crowdService.getTotalNumForCrowd(crowd.getId());
			boolean beCompleted = totalAmount >= crowd.getTargetMoney();
			List<UserCrowd> userCrowdList = userCrowdService.getUserCrowdByCrowdId(crowd.getId(), 100, 0);
			if(userCrowdList!=null){
				while(true){
					for(UserCrowd userCrowd:userCrowdList){
						if(lastMinId > userCrowd.getId()){
							lastMinId = userCrowd.getId();
						}
						userCrowdCount++;
						userCrowdService.dealWithUserCrowd(userCrowd,beCompleted);
					}
					if(userCrowdList.size()<100){
						break;
					}else{
						userCrowdList = userCrowdService.getUserCrowdByCrowdId(crowd.getId(),100,lastMinId);
					}
				}
			}
			//将项目的处理状态设置为已经结算
			crowd.setStatus(0);
			crowdService.saveOrUpdateCrowd(crowd);
		}
		log.info("共执行到期项目笔数:" + crowdCount);
		log.info("共执行到期项目的用户参与项笔数:" + userCrowdCount);
	}
}
