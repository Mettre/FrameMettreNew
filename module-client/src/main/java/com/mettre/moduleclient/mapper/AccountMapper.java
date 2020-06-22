package com.mettre.moduleclient.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.inputPojo.AccountListPojo;
import com.mettre.moduleclient.inputPojo.AccountListPojoPage;
import com.mettre.moduleclient.pojo.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    BigDecimal monthAccountExpenditure(@Param(value = "year") Integer year, @Param(value = "month") Integer month, @Param(value = "userId") String userId);

    BigDecimal monthAccountIncome(@Param(value = "year") Integer year, @Param(value = "month") Integer month, @Param(value = "userId") String userId);

    List<Account> monthAccountList(@Param(value = "year") Integer year, @Param(value = "month") Integer month, @Param(value = "userId") String userId);

    List<Account> searchAccountList(@Param(value = "accountListPojo") AccountListPojo accountListPojo, @Param(value = "userId") String userId);

    List<Account> searchAccountListPage(Page<Account> page, @Param(value = "accountListPojo") AccountListPojoPage accountListPojo, @Param(value = "userId") String userId);

    List<String> recommendTitleList(@Param(value = "type") Integer type, @Param(value = "userId") String userId);

    Integer totalAccountNum(@Param(value = "userId") String userId);

    Integer totalAccountDay(@Param(value = "userId") String userId);
}