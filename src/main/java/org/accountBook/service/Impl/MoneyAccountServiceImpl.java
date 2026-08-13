package org.accountBook.service.Impl;

import org.accountBook.dto.Category;
import org.accountBook.mapper.MoneyAccountMapper;
import org.accountBook.service.MoneyAccountService;
import org.ehcache.spi.service.MaintainableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyAccountServiceImpl implements MoneyAccountService {
    @Autowired
    MoneyAccountMapper moneyAccountMapper;

    @Override
    public void addAccountType(Category category){
        moneyAccountMapper.addTypeName(category);
    }
}
