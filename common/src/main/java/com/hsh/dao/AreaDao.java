package com.hsh.dao;

import java.util.List;

import com.hsh.model.City;
import com.hsh.model.Province;

public interface AreaDao {

	int saveOrUpdateProvince(Province province);
	
	int saveOrUpdateCity(City city);
	
	List<City> getCityListByProvince(int provinceId);
	
}
