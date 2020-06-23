package com.mettre.modulefriend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulefriend.pojo.MomentsDetails;
import com.mettre.modulefriend.pojo.MomentsParameter;
import com.mettre.modulefriend.pojoVM.MomentsListVM;
import com.mettre.modulefriend.pojoVM.MomentsVM;
import com.mettre.modulefriend.pojoVM.PersonalMomentsListVM;
import com.mettre.modulefriend.service.MomentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(description = "朋友圈模块")
@RequestMapping("/moments")
public class MomentsController {

    @Autowired
    public MomentsService momentsService;

    @RequestMapping(value = "/loginEd/addMoments", method = RequestMethod.POST)
    @ApiOperation(value = "发布说说")
    public Result<ResultBean> insert(@Valid @RequestBody MomentsVM momentsVM) {
        momentsService.insert(momentsVM);
        return new Result<>().ok();
    }

    @RequestMapping(value = "/loginEd/findMomentsListWithPublisherUserId", method = RequestMethod.POST)
    @ApiOperation(value = "查询个人发布的说说")
    public Result<Object> findCategoryList(@Valid @RequestBody PersonalMomentsListVM pageUtil) {

        Page<MomentsParameter> page2 = new Page<>(pageUtil.getPage(), pageUtil.getSize());
        Page<MomentsParameter> momentsList = momentsService.selectPageVo(page2, pageUtil.getPublisherUserId());
        return new Result<>().ok(momentsList);
    }

    @RequestMapping(value = "/loginEd/circleFriendsList", method = RequestMethod.POST)
    @ApiOperation(value = "我的朋友圈们")
    public Result<Object> circleFriendsList(@Valid @RequestBody MomentsListVM pageUtil) {

        Page<MomentsParameter> page = new Page<>(pageUtil.getPage(), pageUtil.getSize());
        String userId = SecurityContextStore.getContext().getUserId();
        Page<MomentsParameter> momentsList = momentsService.circleFriendsPageVo(page, userId);
        return new Result<>().ok(momentsList);
    }


    @RequestMapping(value = "/loginEd/selectMomentsDetails/{momentsId}", method = RequestMethod.GET)
    @ApiOperation(value = "说说详情+评论")
    public Result<Object> selectMomentsDetails(@PathVariable String momentsId) {
        MomentsDetails momentsDetails = momentsService.selectMomentsDetails(momentsId);
        return new Result<>().ok(momentsDetails);
    }

}
