package com.mettre.modulefriend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.modulecommon.base.Result;
import com.mettre.modulecommon.base.ResultBean;
import com.mettre.modulecommon.base.ResultList;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import com.mettre.modulefriend.pojo.Reply;
import com.mettre.modulefriend.pojoVM.ReplyVM;
import com.mettre.modulefriend.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@Api(description = "评论")
public class ReplyController {

    @Autowired
    public ReplyService replyService;

    @RequestMapping(value = "/loginEd/addReply", method = RequestMethod.POST)
    @ApiOperation(value = "添加评论")
    public Result<ResultBean> insert(@Valid @RequestBody ReplyVM replyVM) {
        replyService.insert(replyVM);
        return new Result<>().ok();
    }

    @RequestMapping(value = "/findNewsReplyList", method = RequestMethod.POST)
    @ApiOperation(value = "新闻评论列表")
    public Result<Object> findCategoryList(@RequestBody HashMap<String, Object> map) {
        Integer page = Integer.parseInt(map.get("page").toString());
        Integer size = Integer.parseInt(map.get("size").toString());
        String dynamicId = map.get("dynamicId").toString();
        String userId = map.get("userId").toString();
        Page<Reply> page2 = new Page<>(page, size);
        Page<Reply> addressList = replyService.selectPageVo(page2, dynamicId, userId);
        return new Result<>().ok(addressList);
    }

    @RequestMapping(value = "/findMomentsReplyList", method = RequestMethod.GET)
    @ApiOperation(value = "朋友圈评论列表")
    public Result<Object> findMomentsCategoryList(@RequestParam String dynamicId, String userId) {
        List<Reply> replyList = replyService.selectMomentsReply(dynamicId, userId);
        return new Result<>().ok(new ResultList(replyList));
    }

    @RequestMapping(value = "/loginEd/deleteReplyFromUser{replyId}", method = RequestMethod.GET)
    @ApiOperation(value = "用户删除评论")
    public Result<ResultBean> deleteReplyFromUser(@PathVariable String replyId) {
        String userId = SecurityContextStore.getContext().getUserId();
        replyService.deleteByReplyIdAndDynamicUserId(replyId, userId);
        return new Result<>().ok();
    }

    @RequestMapping(value = "/loginEd/deleteReply{replyId}", method = RequestMethod.GET)
    @ApiOperation(value = "删除评论")
    public Result<ResultBean> deleteReply(@PathVariable String replyId) {
        replyService.deleteByPrimaryKey(replyId);
        return new Result<>().ok();
    }

}
