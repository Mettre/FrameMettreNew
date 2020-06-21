package com.mettre.moduleclient.controller;

import com.mettre.moduleclient.inputPojo.AccountPojo;
import com.mettre.moduleclient.pojo.Account;
import com.mettre.moduleclient.service.BookkeepingService;
import com.mettre.modulecommon.base.Result;
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
public class BookkeepingController {


    @Autowired
    BookkeepingService bookkeepingService;


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
        return Result.ok(new ResultBean());
    }
}
