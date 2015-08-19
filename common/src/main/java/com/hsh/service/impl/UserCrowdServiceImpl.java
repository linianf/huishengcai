package com.hsh.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hsh.dao.UserCrowdDao;
import com.hsh.dao.WalletDao;
import com.hsh.model.QuanGetRecord;
import com.hsh.model.UserCrowd;
import com.hsh.model.WalletFlow;
import com.hsh.service.UserCrowdService;
import com.hsh.util.Constants;
import com.hsh.util.HSHUtil;
import com.hsh.util.RedisUtil;
import com.hsh.util.TradeType;
import com.hsh.vo.QuanVO;

/**
 * 
 * @author linianf
 *
 */
@Service("userCrowdService")
@Transactional(readOnly=true)
public class UserCrowdServiceImpl implements UserCrowdService{

	@Autowired
	private UserCrowdDao userCrowdDao;
	
	@Autowired
	private WalletDao walletDao;
	
	@Autowired
	private RedisUtil redisUtil;

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdate(UserCrowd userCrowd) {
		return userCrowdDao.saveOrUpdate(userCrowd);
	}

	@Override
	@Transactional(readOnly=false)
	public long saveOrUpdate(QuanGetRecord quanGetRecord) {
		return userCrowdDao.saveOrUpdate(quanGetRecord);
	}
	
	@Override
	public List<UserCrowd> getInCrowdList(int userId) {
		return userCrowdDao.getInCrowdList(userId);
	}

	@Override
	public List<UserCrowd> getEndCrowdList(int userId, int pageSize,
			int lastMinUserCrowdId) {
		return userCrowdDao.getEndCrowdList(userId, pageSize, lastMinUserCrowdId);
	}

	@Override
	public List<UserCrowd> getUserCrowdByCrowdId(int crowdId, int pageSize, int lastMinUserCrowdId) {
		return userCrowdDao.getUserCrowdByCrowdId(crowdId, pageSize, lastMinUserCrowdId);
	}

	@Override
	public List<UserCrowd> getInCrowdList(int pageSize, int lastMinUserCrowdId) {
		return userCrowdDao.getInCrowdList(pageSize,lastMinUserCrowdId);
	}

	/**
	 * 处理到期的用户项目
	 * 1.退还本金至钱包账户 
	 * 2.(如果项目完成目标金额)根据项目参与天数发放惠生活兑换券
	 */
	@Override
	@Transactional(readOnly=false)
	public void dealWithUserCrowd(UserCrowd userCrowd,boolean beCompleted) {
		if(userCrowd.getStatus()==0){
			return;
		}
		int amount = userCrowd.getAmount();
		Date buyDate = userCrowd.getBuyDate();
		int hshQuan = userCrowd.getHshQuan();
		int quanDays = userCrowd.getQuanDays();
		int userId = userCrowd.getUserId();
		//返还项目金额至钱包账户
		walletDao.addUserWallet(userId, amount);
		//增加流水
		WalletFlow flow = new WalletFlow();
		flow.setAmount(amount);
        flow.setInOut(1);
        flow.setOperateDate(new Date());
        flow.setUserId(userId);
        flow.setTradeType(TradeType.CROWD_OVER);
        walletDao.saveOrUpdate(flow);
        if(beCompleted){
	        //计算项目实际购买天数
	        int days = HSHUtil.calacDaysFrom2Date(buyDate, new Date());
	        int  realAmount = (days/quanDays)*hshQuan;
	        QuanVO quanVO = new QuanVO();
	        quanVO.setAmount(realAmount);
	        quanVO.setItemId(userCrowd.getItemId());
	        quanVO.setCrowdId(userCrowd.getCrowdId());
	        quanVO.setUserId(userId);
	        quanVO.setMobile(userCrowd.getMobile());
	        redisUtil.lpush(Constants.QUAN_QUEUE,JSON.toJSONString(quanVO));  
        }
        //将用户的参与项状态设置为已结算
        userCrowd.setStatus(0);
        userCrowdDao.saveOrUpdate(userCrowd);
	}

	

}
