package com.mettre.moduleclientb.controller;

import com.mettre.moduleclientb.feign.UserRpcService;
import com.mettre.moduleclientb.service.BookkeepingService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.pojo.User;
import com.mettre.modulecommon.resultUtil.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 记账
 */

@Api(description = "记账信息")
@RestController
public class BookkeepingController {

    @Autowired
    UserRpcService userRpcService;

    @Autowired
    BookkeepingService bookkeepingService;


    @ApiOperation("添加记账本")
    @RequestMapping(value = "/loginEd/addBookkeeping", method = RequestMethod.POST)
    public Result<Object> insert() {
        String id = SecurityContextStore.getContext().getUserId();
        Result<User> user = userRpcService.findUserInfo(id);
        if (ResultUtils.getInfoSuccess(user)) ;
        return Result.ok()
    }
}
