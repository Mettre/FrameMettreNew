package com.mettre.moduleclient.controller;


import com.mettre.moduleclient.pojo.AccountClassification;
import com.mettre.moduleclient.service.AccountClassificationService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api("记账分类")
@RequestMapping("/accountClassification")
public class AccountClassificationController {

    @Autowired
    AccountClassificationService accountClassificationService;

    @ApiOperation(value = "添加记账分类")
    @PostMapping(value = "/add")
    public Result<ResultBean> addClassification(@Valid @RequestBody AccountClassification accountClassification) {
        accountClassificationService.insert(accountClassification);
        return Result.ok();
    }

    @ApiOperation(value = "修改记账分类")
    @PostMapping(value = "/update")
    public Result<ResultBean> updateClassification(@Valid @RequestBody AccountClassification accountClassification) {
        accountClassificationService.updateByPrimaryKey(accountClassification);
        return Result.ok();
    }


}
