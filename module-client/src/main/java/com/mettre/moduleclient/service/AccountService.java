package com.mettre.moduleclient.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.inputPojo.AccountListPojo;
import com.mettre.moduleclient.inputPojo.AccountListPojoPage;
import com.mettre.moduleclient.inputPojo.AccountPojo;
import com.mettre.moduleclient.mapper.AccountMapper;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.moduleclient.pojo.*;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountService {

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public AccountMapper accountMapper;

    @Autowired
    public AccountClassificationService accountClassificationService;


    public int deleteByPrimaryKey(Integer id) {
        int type = accountMapper.deleteByPrimaryKey(id);
        return ReturnType.ReturnType(type, "删除失败");
    }


    public int insert(AccountPojo accountPojo) {
        String userId = SecurityContextStore.getContext().getUserId();
        AccountClassification accountClassification = accountClassificationService.selectByPrimaryKey(accountPojo.getClassification());
        if (accountClassification == null) {
            throw new CustomerException("分类错误");
        }
        int type = accountMapper.insert(new Account(accountPojo, userId));
        return ReturnType.ReturnType(type, "添加失败");
    }

    /**
     * 统计月份记账金额情况
     *
     * @param month
     * @param userId
     * @return
     */
    public MonthAccount monthAccountList(Integer year, Integer month, String userId) {
        List<AccountList> accountList = accountMapper.monthAccountList(year, month, userId);
        for (int i = 0; i < accountList.size(); i++) {
            accountList.set(i, new AccountList(accountList.get(i).getAccountBeans(), accountList.get(i).getRecordDay()));
        }

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
