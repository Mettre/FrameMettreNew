package com.mettre.moduleclient.controller;

import com.mettre.moduleclient.inputPojo.AccountPojo;
import com.mettre.moduleclient.pojo.Account;
import com.mettre.moduleclient.service.StatisticsService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/statistics")
@Api(description = "统计")
public class StatisticsController {

    @Autowired
    public StatisticsService statisticsService;


}
