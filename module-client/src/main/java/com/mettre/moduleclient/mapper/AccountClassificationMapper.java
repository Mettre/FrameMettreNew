package com.mettre.moduleclient.mapper;

import com.mettre.moduleclient.pojo.AccountClassification;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountClassificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountClassification record);

    int insertSelective(AccountClassification record);

    AccountClassification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountClassification record);

    int updateByPrimaryKey(AccountClassification record);
}