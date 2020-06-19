package com.mettre.moduleclientb.controller;

import com.mettre.moduleclientb.feign.UserRpcService;
import com.mettre.moduleclientb.service.StatisticsService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.pojo.User;
import com.mettre.modulecommon.resultUtil.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "统计信息")
@RestController
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    UserRpcService userRpcService;

    @RequestMapping(value = "/loginEd/monthMonthlyExpenditure/{month}", method = RequestMethod.GET)
    @ApiOperation("月份支出")
    public void MonthMonthlyExpenditure(@PathVariable("month") String month) {

        String id = SecurityContextStore.getContext().getUserId();
        Result<User> userResult = userRpcService.findUserInfo(id);
        if (ResultUtils.getInfoSuccess(userResult)) ;


    }

}
