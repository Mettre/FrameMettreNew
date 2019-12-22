package com.mettre.moduleclient.controller;

import com.mettre.moduleclient.service.LoginService;
import com.mettre.modulecommon.base.Result;
import com.mettre.pojoVM.UserRegisterVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(description = "登录信息")
public class LoginController {

    @Autowired
    public LoginService loginService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> insert(@Valid @RequestBody UserRegisterVM userVM) {
        loginService.insert(userVM);
        return Result.ok("注册成功");
    }


//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ApiOperation(value = "用户登录")
//    public Result<Object> login(@RequestParam String phone, @RequestParam String password) {
//
//        return Result.ok(loginService.in);
//    }


}
