package com.hsh.service;

import java.util.List;

import com.hsh.model.Bank;
import com.hsh.model.SubBank;

public interface BankService {

    int saveOrUpdateBank(Bank bank);
    
    int saveOrUpdateSubBank(SubBank subBank);
    
    List<SubBank> getSubBankListByCityAndBank(int bankId, int cityId);
}
