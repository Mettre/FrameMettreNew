package com.mettre.moduleclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.inputPojo.AccountListPojo;
import com.mettre.moduleclient.inputPojo.AccountListPojoPage;
import com.mettre.moduleclient.inputPojo.AccountPojo;
import com.mettre.moduleclient.inputPojo.monthBillPojo;
import com.mettre.moduleclient.pojo.Account;
import com.mettre.moduleclient.pojo.AccountClassification;
import com.mettre.moduleclient.pojo.AccountStatisticsBean;
import com.mettre.moduleclient.pojo.MonthAccount;
import com.mettre.moduleclient.service.AccountClassificationService;
import com.mettre.moduleclient.service.AccountService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultList;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.base.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
@Api(description = "记账")
public class AccountController {

    @Autowired
    public AccountService accountService;

    @ApiOperation(value = "添加记账")
    @PostMapping(value = "/loginEd/add")
    public Result<ResultBean> addBookkeeping(@Valid @RequestBody AccountPojo accountPojo) {

        accountService.insert(accountPojo);
        return Result.ok();
    }

    @ApiOperation(value = "删除记账")
    @GetMapping(value = "/loginEd/delete/{id}")
    public Result<ResultBean> deleteByPrimaryKey(@PathVariable Integer id) {
        accountService.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @ApiOperation(value = "记账列表-分页")
    @PostMapping(value = "/loginEd/getAccountListPage")
    public Result<Object> searchAccountListPage(@Valid @RequestBody AccountListPojoPage accountListPojo) {
        String userId = SecurityContextStore.getContext().getUserId();
        Page<Account> page = new Page<>(accountListPojo.getPage(), accountListPojo.getSize());
        return Result.ok(accountService.searchAccountListPage(page, accountListPojo, userId));
    }

    @ApiOperation(value = "记账列表")
    @PostMapping(value = "/loginEd/getAccountList")
    public Result<Object> searchAccountList(@RequestBody AccountListPojo accountListPojo) {
        String userId = SecurityContextStore.getContext().getUserId();
        return Result.ok(new ResultList(accountService.searchAccountList(accountListPojo, userId)));
    }

    @ApiOperation(value = "统计月份记账记录")
    @PostMapping(value = "/loginEd/statisticsMonth")
    public Result<Object> monthBill(@Valid @RequestBody monthBillPojo monthBillPojo) {
        String userId = SecurityContextStore.getContext().getUserId();
        MonthAccount monthAccount = accountService.monthAccountList(monthBillPojo.getYear(), monthBillPojo.getMonth(), userId);
        return Result.ok(monthAccount);
    }

    @ApiOperation(value = "推荐标题排序")
    @GetMapping(value = "/loginEd/recommendTitle/{type}")
    public Result<Object> recommendTitle(@PathVariable Integer type) {
        String userId = SecurityContextStore.getContext().getUserId();
        List<String> recommendTitleList = accountService.recommendTitle(type, userId);
        return Result.ok(new ResultList(recommendTitleList));
    }

    @ApiOperation(value = "记账统计信息")
    @GetMapping(value = "/loginEd/statistics")
    public Result<Object> accountStatisticsBean() {
        String userId = SecurityContextStore.getContext().getUserId();
        return Result.ok(accountService.accountStatisticsBean(userId));
    }
}
