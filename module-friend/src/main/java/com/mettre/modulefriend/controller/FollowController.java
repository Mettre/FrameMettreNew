package com.mettre.modulefriend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import com.mettre.modulecommon.base.ResultList;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.pojo.BasePage;
import com.mettre.modulefriend.pojo.Follow;
import com.mettre.modulefriend.pojoVM.FollowVM;
import com.mettre.modulefriend.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(description = "关注")
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    public FollowService followService;

    @RequestMapping(value = "/loginEd/addFollow", method = RequestMethod.POST)
    @ApiOperation(value = "添加关注")
    public Result<ResultBean> addFollow(@Valid @RequestBody FollowVM followVM) {
        followService.insert(followVM);
        return Result.ok();
    }

    @RequestMapping(value = "/loginEd/cancelFollow", method = RequestMethod.POST)
    @ApiOperation(value = "取消关注")
    public Result<ResultBean> cancelFollow(@Valid @RequestBody FollowVM followVM) {
        followService.cancelFollow(followVM);
        return Result.ok();
    }

    @RequestMapping(value = "/loginEd/myFriendsPo", method = RequestMethod.POST)
    @ApiOperation(value = "我的好友列表分页")
    public Result<Object> myFriendsPageVo(@RequestBody BasePage basePage) {
        String userId = SecurityContextStore.getContext().getUserId();
        Page<Follow> page = new Page<>(basePage.getPage(), basePage.getSize());
        return Result.ok(followService.myFriendsPageVo(page, userId));
    }

    @RequestMapping(value = "/loginEd/myFriendsList", method = RequestMethod.GET)
    @ApiOperation(value = "我的好友列表")
    public Result<Object> myFriendsList() {
        String userId = SecurityContextStore.getContext().getUserId();
        return Result.ok(new ResultList(followService.myFriendsList(userId)));
    }

    @RequestMapping(value = "/loginEd/myFollowList", method = RequestMethod.POST)
    @ApiOperation(value = "我的关注列表识别互关")
    public Result<Object> myFollowList(@RequestBody BasePage basePage) {
        String userId = SecurityContextStore.getContext().getUserId();
        Page<Follow> page = new Page<>(basePage.getPage(), basePage.getSize());
        return Result.ok(followService.myFollowPageVo(page, userId));
    }

    @RequestMapping(value = "/loginEd/myFansList", method = RequestMethod.POST)
    @ApiOperation(value = "我的粉丝列表识别互关")
    public Result<Object> myFansList(@RequestBody BasePage basePage) {
        String userId = SecurityContextStore.getContext().getUserId();
        Page<Follow> page = new Page<>(basePage.getPage(), basePage.getSize());
        return Result.ok(followService.myFansPageVo(page, userId));
    }

    @RequestMapping(value = "/loginEd/findUserList", method = RequestMethod.GET)
    @ApiOperation(value = "搜索用户")
    public Result<Object> findUserList(@RequestParam String findUserId) {
        return Result.ok(followService.findUserList(findUserId));
    }

}
