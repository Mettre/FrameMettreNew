package com.mettre.modulefriend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulecommon.pojo.BasePage;
import com.mettre.modulefriend.pojo.Visitor;
import com.mettre.modulefriend.pojoVM.VisitorVM;
import com.mettre.modulefriend.service.VisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(description = "空间访问")
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    public VisitorService visitorService;

    @RequestMapping(value = "/loginEd/addVisitor", method = RequestMethod.POST)
    @ApiOperation(value = "访问别人的空间")
    public Result<ResultBean> insert(@Valid @RequestBody VisitorVM visitorVM) {
        String userId = SecurityContextStore.getContext().getUserId();
        visitorService.insert(new Visitor(visitorVM, userId));
        return new Result<>().ok();
    }

    @RequestMapping(value = "/loginEd/myVisitorList", method = RequestMethod.POST)
    @ApiOperation(value = "我的空间访问记录")
    public Result<Object> myVisitorList(@RequestBody BasePage basePage) {
        String visitorsUser = SecurityContextStore.getContext().getUserId();
        Page<Visitor> page = new Page<>(basePage.getPage(), basePage.getSize());
        return new Result<>().ok(visitorService.myVisitorPageVo(page, visitorsUser));
    }

    @RequestMapping(value = "/loginEd/deletePersonalVisitor", method = RequestMethod.POST)
    @ApiOperation(value = "主人删除单个访客全部空间来访记录")
    public Result<ResultBean> deletePersonalVisitor(@RequestParam(value = "visitorId", required = false) Long visitorId) {
        visitorService.deleteAllVisitorFromVisitorId(visitorId);
        return new Result<>().ok();
    }

    @RequestMapping(value = "/deleteStripVisitor", method = RequestMethod.GET)
    @ApiOperation(value = "删除空间访问某单条记录")
    public Result<ResultBean> deleteStripVisitor(@RequestParam Long visitorId) {
        visitorService.deleteByPrimaryKey(visitorId);
        return new Result<>().ok();
    }

}
