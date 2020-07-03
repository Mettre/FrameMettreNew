package com.mettre.moduleclient.service;

import com.mettre.moduleclient.mapper.AccountClassificationMapper;
import com.mettre.moduleclient.pojo.AccountClassification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountClassificationService {

    @Autowired
    AccountClassificationMapper accountClassificationMapper;

    public int insert(AccountClassification accountClassification) {
        return accountClassificationMapper.insert(new AccountClassification(accountClassification, true));
    }

    public AccountClassification selectByPrimaryKey(Integer id) {
        return accountClassificationMapper.selectByPrimaryKey(id);
    }

    public List<AccountClassification> accountClassificationList(Integer type) {
        List<AccountClassification> accountList = (List<AccountClassification>) accountClassificationMapper.accountClassificationList(type);
        return accountList;
    }

    public int updateByPrimaryKeySelective(AccountClassification accountClassification) {
        return accountClassificationMapper.updateByPrimaryKeySelective(new AccountClassification(accountClassification, false));
    }
}
