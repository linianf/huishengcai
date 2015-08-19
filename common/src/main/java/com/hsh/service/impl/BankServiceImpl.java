package com.hsh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsh.dao.BankDao;
import com.hsh.model.Bank;
import com.hsh.model.SubBank;
import com.hsh.service.BankService;

/**
 * 
 * @author linianf
 *
 */

@Service("bankService")
@Transactional(readOnly=true)
public class BankServiceImpl implements BankService{

	@Autowired
	private BankDao bankDao;

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdateBank(Bank bank) {
		return bankDao.saveOrUpdateBank(bank);
	}

	@Override
	@Transactional(readOnly=false)
	public int saveOrUpdateSubBank(SubBank subBank) {
		return bankDao.saveOrUpdateSubBank(subBank);
	}

	@Override
	public List<SubBank> getSubBankListByCityAndBank(int bankId, int cityId) {
		return bankDao.getSubBankListByCityAndBank(bankId, cityId);
	}
}
