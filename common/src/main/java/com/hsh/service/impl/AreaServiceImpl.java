package com.hsh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsh.dao.AreaDao;
import com.hsh.model.City;
import com.hsh.model.Province;
import com.hsh.service.AreaService;

/**
 * 
 * @author linianf
 *
 */
@Service("areaService")
@Transactional(readOnly=true)
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaDao areaDao;
	
	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdateProvince(Province province) {
		return areaDao.saveOrUpdateProvince(province);
	}

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdateCity(City city) {
		return areaDao.saveOrUpdateCity(city);
	}

	@Override
	public List<City> getCityListByProvince(int provinceId) {
		return areaDao.getCityListByProvince(provinceId);
	}

}
