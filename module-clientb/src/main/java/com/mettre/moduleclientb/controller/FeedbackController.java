package com.mettre.moduleclientb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclientb.pojo.Feedback;
import com.mettre.moduleclientb.pojoVM.FeedbackModifyVM;
import com.mettre.moduleclientb.pojoVM.FeedbackSearchVM;
import com.mettre.moduleclientb.pojoVM.FeedbackVM;
import com.mettre.moduleclientb.service.FeedbackService;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/feedback")
@Api(description = "用户反馈")
public class FeedbackController {

    @Autowired
    public FeedbackService feedbackService;

    @RequestMapping(value = "/loginEd/addFeedback", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户反馈")
    public Result<ResultBean> addFeedback(@Valid @RequestBody FeedbackVM feedbackVM) {
        feedbackService.insert(feedbackVM);
        return Result.ok();
    }

    @RequestMapping(value = "/handleFeedback", method = RequestMethod.POST)
    @ApiOperation(value = "处理反馈状态")
    public Result<ResultBean> handleFeedback(@Valid @RequestBody FeedbackModifyVM feedbackVM) {
        feedbackService.updateByPrimaryKeySelective(feedbackVM);
        return Result.ok();
    }

    @RequestMapping(value = "/loginEd/FeedbackPageVo", method = RequestMethod.POST)
    @ApiOperation(value = "查询反馈列表")
    public Result<Object> findFeedbackList(@Valid @RequestBody FeedbackSearchVM feedbackSearchVM) {
        Page<Feedback> page = new Page<>(feedbackSearchVM.getPage(), feedbackSearchVM.getSize());
        return new Result().result(feedbackService.findFeedbackListPageVo(page, feedbackSearchVM));
    }


    @RequestMapping(value = "/loginEd/deleteFeedback", method = RequestMethod.POST)
    @ApiOperation(value = "删除反馈")
    public Result<ResultBean> deleteFeedback(@RequestParam Long feedbackId) {
        feedbackService.deleteByPrimaryKey(feedbackId);
        return Result.ok();
    }

}
