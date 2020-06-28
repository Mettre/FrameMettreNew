package com.mettre.moduleclient.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclient.mapper.UserMapper;
import com.mettre.moduleclient.service.UserService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.pojo.User;
import com.mettre.modulecommon.pojoVm.*;
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
    public UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> insert(@Valid @RequestBody UserRegisterVM userVM) {
        return Result.ok(userService.insert(userVM));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result<Object> login(@RequestParam String phone, @RequestParam String password) {
        return Result.ok(userService.selectByPhoneAndPassword(phone, password));
    }

    @RequestMapping(value = "/loginEd/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取个人信息")
    public Result<Object> findUserInfo() {
        String userId = SecurityContextStore.getContext().getUserId();
        return Result.ok(userService.selectByPrimaryKey(userId));
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "根据userId查询个人信息")
    public Result<Object> findUserInfoWithUserId(@RequestParam String userId) {
        return Result.ok(userService.selectByPrimaryKey(userId));
    }

    @RequestMapping(value = "/loginEd/modifyUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "修改个人信息")
    public Result<Object> modifyUserInfo(@RequestBody UserVM userVM) {
        String userId = SecurityContextStore.getContext().getUserId();
        userService.updateByPrimaryKeySelective(userVM, userId);
        return Result.ok();
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    @ApiOperation(value = "忘记密码")
    public Result<Object> forgetPassword(@Valid @RequestBody ForgetPasswordVM userVM) {
        userService.forgetPassword(userVM);
        return Result.ok();
    }

    @RequestMapping(value = "/loginEd/modifyPassword", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码")
    public Result<Object> modifyPassword(@Valid @RequestBody ModifyPasswordVM userVM) {
        userService.modifyPassword(userVM);
        return Result.ok();
    }

    @RequestMapping(value = "/searchByPhone", method = RequestMethod.GET)
    @ApiOperation(value = "根据手机号模糊搜索用户")
    public Result<Object> searchByPhone(String phoneStr) {
        List<User> userList = userService.searchByPhone(phoneStr);
        return Result.ok(userList);
    }

    @RequestMapping(value = "/selectByPhoneByPage", method = RequestMethod.POST)
    @ApiOperation(value = "根据手机号模糊搜索用户 -- 分页")
    public Result<Object> selectByPhoneByPage(@RequestBody selectByPhoneVM basePage) {
        Page<User> page = new Page<>(basePage.getPage(), basePage.getSize());
        return Result.ok(userService.searchByPhoneByPage(page, basePage));
    }

    @RequestMapping(value = "/selectByPhone", method = RequestMethod.GET)
    @ApiOperation(value = "根据手机号搜索用户")
    public Result<Object> selectByPhone(String phoneStr) {
        return Result.ok(new User(userService.selectByPhone(phoneStr)));
    }


}
