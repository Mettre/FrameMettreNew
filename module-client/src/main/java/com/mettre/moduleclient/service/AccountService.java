package com.mettre.moduleclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.inputPojo.AccountListPojo;
import com.mettre.moduleclient.inputPojo.AccountListPojoPage;
import com.mettre.moduleclient.mapper.AccountMapper;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.moduleclient.pojo.Account;
import com.mettre.moduleclient.pojo.AccountStatisticsBean;
import com.mettre.moduleclient.pojo.MonthAccount;
import com.mettre.modulecommon.base.ReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountService {

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


    /**
     * 统计月份记账金额情况
     *
     * @param month
     * @param userId
     * @return
     */
    public MonthAccount monthAccountList(Integer year, Integer month, String userId) {
        List<Account> accountList = accountMapper.monthAccountList(year, month, userId);
        BigDecimal expenditure = accountMapper.monthAccountExpenditure(year, month, userId);
        BigDecimal income = accountMapper.monthAccountIncome(year, month, userId);
        return new MonthAccount(accountList, expenditure, income);
    }

    public List<Account> searchAccountList(AccountListPojo accountListPojo, String userId) {
        List<Account> accountList = (List<Account>) accountMapper.searchAccountList(accountListPojo, userId);
        return accountList;
    }

    public Page<Account> searchAccountListPage(Page<Account> page, AccountListPojoPage accountListPojo, String userId) {
        List<Account> accountList = (List<Account>) accountMapper.searchAccountListPage(page, accountListPojo, userId);
        return page.setRecords(accountList);
    }

    /**
     * 推荐标题
     *
     * @return
     */
    public List<String> recommendTitle(Integer type, String userId) {
        List<String> recommendTitleList = accountMapper.recommendTitleList(type, userId);
        return recommendTitleList;
    }

    /**
     * 记账统计信息
     *
     * @return
     */
    public AccountStatisticsBean accountStatisticsBean(String userId) {
        Integer totalAccountNum = accountMapper.totalAccountNum(userId);
        Integer totalAccountDay = accountMapper.totalAccountDay(userId);
        return new AccountStatisticsBean(totalAccountNum, totalAccountDay);
    }
}
