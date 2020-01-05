package com.mettre.moduleclientb.controller;

import com.mettre.moduleclientb.feign.UserRpcService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "收货信息")
public class ReceivingController {

    @Autowired
    UserRpcService userFeign;

    @RequestMapping(value = "/loginEd/addReceivingAddress", method = RequestMethod.GET)
    @ApiOperation(value = "添加收货地址")
    public Result<Object> insert() {
        String userId = SecurityContextStore.getContext().getUserId();
        User user = userFeign.findUserInfo(userId);
        return Result.ok(user);
    }

}
