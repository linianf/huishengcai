package com.hsh.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsh.dao.OrderDao;
import com.hsh.model.Recharge;
import com.hsh.model.Transfer;
import com.hsh.service.OrderService;
import com.hsh.util.HSHUtil;

/**
 * 
 * @author linianf
 *
 */
@Service("orderService")
@Transactional(readOnly=true)
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Override
	@Transactional(readOnly=false)
	public long saveOrUpdateRecharge(Recharge recharge) {
		return orderDao.saveOrUpdateRecharge(recharge);
	}

	@Override
	@Transactional(readOnly=false)
	public long saveOrUpdateRecharge(Transfer transfer) {
		return orderDao.saveOrUpdateRecharge(transfer);
	}
    
	/**
	 * 获取待处理的提现申请
	 * 
	 * 采用T+1方式处理提现，即用户今日发起提现申请，系统明日发送给银行
	 * 为避免检索结果集过长，采用检索今天0点以前，加上前天0点以后的数据
	 */
	@Override
	public List<Transfer> getTransferList(int lastMinId, int pageSize) {
		Date now = new Date();
		String toDate = HSHUtil.calacDaysFromToday(now);
		String fromDate = HSHUtil.calacDaysFrom2beforeToday(now);
		return orderDao.getTransferList(fromDate, toDate, lastMinId, pageSize);
	}

	@Override
	public Transfer getTransferById(long id) {
		return orderDao.getTransferById(id);
	}

	@Override
	public Recharge getRechargeById(long id) {
		return orderDao.getRechargeById(id);
	}

}
