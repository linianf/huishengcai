package com.hsh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsh.dao.CrowdDao;
import com.hsh.model.Crowd;
import com.hsh.model.CrowdItem;
import com.hsh.model.CrowdPic;
import com.hsh.service.CrowdService;

/**
 * 
 * @author linianf
 *
 */
@Service("crowdService")
@Transactional(readOnly=true)
public class CrowdServiceImpl implements CrowdService{

	@Autowired
	private CrowdDao crowdDao;

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdateCrowd(Crowd crowd) {
		return crowdDao.saveOrUpdateCrowd(crowd);
	}

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdateItem(CrowdItem item) {
		return crowdDao.saveOrUpdateItem(item);
	}

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdatePic(CrowdPic crowdPic) {
		return crowdDao.saveOrUpdatePic(crowdPic);
	}

	@Override
	public List<CrowdItem> getItemsByCrowdId(int crowdId) {
		return crowdDao.getItemsByCrowdId(crowdId);
	}

	@Override
	public Crowd getCrowdById(int crowdId) {
		return crowdDao.getCrowdById(crowdId);
	}

	@Override
	public List<CrowdPic> getPicListByCrowdId(int crowdId) {
		return crowdDao.getPicListByCrowdId(crowdId);
	}
	
	@Override
	public List<Crowd> getAllCrowdDue(){
		return crowdDao.getAllCrowdDue();
	}

	@Override
	public int getTotalNumForCrowd(int crowdId) {
		return crowdDao.getTotalNumForCrowd(crowdId);
	}
}
