package com.mettre.moduleclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.moduleclient.pojo.UserRegisterVM;
import com.mettre.moduleclient.pojo.selectByPhoneVM;
import com.mettre.moduleclient.service.LoginService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(description = "登录信息")
public class LoginController {

    @Autowired
    public LoginService loginService;

    @Autowired
    public UserMapper userMapper;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> insert(@Valid @RequestBody UserRegisterVM userVM) {
        loginService.insert(userVM);
        return Result.ok("注册成功");
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result<Object> login(@RequestParam String phone, @RequestParam String password) {

        return Result.ok(loginService.selectByPhoneAndPassword(phone, password));
    }

    @RequestMapping(value = "/loginEd/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取个人信息")
    public Result<Object> findUserInfo() {
        String userId = SecurityContextStore.getContext().getUserId();
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new CustomerException("个人信息获取失败");
        }
        User user2 = new User(user);
        return Result.ok(user2);
    }

    @RequestMapping(value = "/searchByPhone", method = RequestMethod.GET)
    @ApiOperation(value = "根据手机号模糊搜索用户")
    public Result<Object> searchByPhone(String phoneStr) {
        List<User> userList = loginService.searchByPhone(phoneStr);
        return Result.ok(userList);
    }

    @RequestMapping(value = "/selectByPhoneByPage", method = RequestMethod.POST)
    @ApiOperation(value = "根据手机号模糊搜索用户 -- 分页")
    public Result<Object> selectByPhoneByPage(@RequestBody selectByPhoneVM basePage) {
        Page<User> page = new Page<>(basePage.getPage(), basePage.getSize());
        return Result.ok(loginService.searchByPhoneByPage(page, basePage));
    }

    @RequestMapping(value = "/selectByPhone", method = RequestMethod.GET)
    @ApiOperation(value = "根据手机号搜索用户")
    public Result<Object> selectByPhone(String phoneStr) {
        return Result.ok(new User(loginService.selectByPhone(phoneStr)));
    }


}
