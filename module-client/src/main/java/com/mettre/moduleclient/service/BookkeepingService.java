package com.mettre.moduleclient.service;

import com.mettre.moduleclient.mapper.AccountMapper;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.moduleclient.pojo.Account;
import com.mettre.modulecommon.base.ReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookkeepingService implements AccountMapper {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        int type = accountMapper.deleteByPrimaryKey(id);
        return ReturnType.ReturnType(type, "删除失败");
    }

    @Override
    public int insert(Account account) {
        int type = accountMapper.insert(account);
        return ReturnType.ReturnType(type, "添加失败");
    }

    @Override
    public int insertSelective(Account record) {
        return 0;
    }

    @Override
    public Account selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Account record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Account record) {
        return 0;
    }
}
