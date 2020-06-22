package com.mettre.moduleclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.inputPojo.AccountListPojo;
import com.mettre.moduleclient.inputPojo.AccountListPojoPage;
import com.mettre.moduleclient.mapper.AccountMapper;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.moduleclient.pojo.Account;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookkeepingService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public AccountMapper accountMapper;


    public int deleteByPrimaryKey(Integer id) {
        int type = accountMapper.deleteByPrimaryKey(id);
        return ReturnType.ReturnType(type, "删除失败");
    }


    public int insert(Account account) {
        int type = accountMapper.insert(account);
        return ReturnType.ReturnType(type, "添加失败");
    }

    public int insertSelective(Account record) {
        return 0;
    }

    public Account selectByPrimaryKey(Integer id) {
        return null;
    }


    public int updateByPrimaryKeySelective(Account record) {
        return 0;
    }


    public int updateByPrimaryKey(Account record) {
        return 0;
    }


    public List<Account> searchAccountList(AccountListPojo accountListPojo, String userId) {
        List<Account> accountList = (List<Account>) accountMapper.searchAccountList(accountListPojo, userId);
        return accountList;
    }

    public Page<Account> searchAccountListPage(Page<Account> page, AccountListPojoPage accountListPojo, String userId) {
        List<Account> accountList = (List<Account>) accountMapper.searchAccountListPage(page, accountListPojo, userId);
        return page.setRecords(accountList);
    }
}
