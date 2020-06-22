package com.mettre.moduleclient.service;

import com.mettre.moduleclient.mapper.AccountClassificationMapper;
import com.mettre.moduleclient.pojo.AccountClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountClassificationService implements AccountClassificationMapper {

    @Autowired
    AccountClassificationMapper accountClassificationMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(AccountClassification accountClassification) {
        return accountClassificationMapper.insert(new AccountClassification(accountClassification, true));
    }

    @Override
    public int insertSelective(AccountClassification record) {
        return 0;
    }

    @Override
    public AccountClassification selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(AccountClassification accountClassification) {
        return accountClassificationMapper.insert(new AccountClassification(accountClassification, false));
    }

    @Override
    public int updateByPrimaryKey(AccountClassification record) {
        return 0;
    }
}
