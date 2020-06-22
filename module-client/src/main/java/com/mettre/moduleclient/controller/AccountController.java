package com.mettre.moduleclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.inputPojo.AccountListPojo;
import com.mettre.moduleclient.inputPojo.AccountListPojoPage;
import com.mettre.moduleclient.inputPojo.AccountPojo;
import com.mettre.moduleclient.pojo.Account;
import com.mettre.moduleclient.service.AccountService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultListBean;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.base.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bookkeeping")
@Api(description = "记账")
public class AccountController {


    @Autowired
    public AccountService bookkeepingService;


    @ApiOperation(value = "添加记账")
    @PostMapping(value = "/loginEd/add")
    public Result<ResultBean> addBookkeeping(@Valid @RequestBody AccountPojo accountPojo) {
        String userId = SecurityContextStore.getContext().getUserId();
        bookkeepingService.insert(new Account(accountPojo, Integer.parseInt(userId)));
        return Result.ok();
    }

    @ApiOperation(value = "删除记账")
    @GetMapping(value = "/loginEd/delete/{id}")
    public Result<ResultBean> deleteByPrimaryKey(@PathVariable Integer id) {
        bookkeepingService.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @ApiOperation(value = "记账列表-分页")
    @PostMapping(value = "/loginEd/getAccountListPage")
    public Result<Object> searchAccountListPage(@Valid @RequestBody AccountListPojoPage accountListPojo) {
        String userId = SecurityContextStore.getContext().getUserId();
        Page<Account> page = new Page<>(accountListPojo.getPage(), accountListPojo.getSize());
        return Result.ok(bookkeepingService.searchAccountListPage(page, accountListPojo, userId));
    }

    @ApiOperation(value = "记账列表")
    @PostMapping(value = "/loginEd/getAccountList")
    public Result<Object> searchAccountList(@RequestBody AccountListPojo accountListPojo) {
        String userId = SecurityContextStore.getContext().getUserId();
        return Result.ok(new ResultListBean(bookkeepingService.searchAccountList(accountListPojo, userId)));
    }
}
