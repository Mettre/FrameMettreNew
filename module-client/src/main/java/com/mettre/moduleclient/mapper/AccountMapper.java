package com.mettre.moduleclient.mapper;

import com.mettre.moduleclient.pojo.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}