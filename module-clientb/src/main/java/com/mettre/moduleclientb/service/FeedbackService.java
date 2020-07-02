package com.mettre.moduleclientb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mettre.moduleclientb.feign.UserRpcService;
import com.mettre.moduleclientb.mapper.FeedbackMapper;
import com.mettre.moduleclientb.pojo.Feedback;
import com.mettre.moduleclientb.pojoVM.FeedbackModifyVM;
import com.mettre.moduleclientb.pojoVM.FeedbackSearchVM;
import com.mettre.moduleclientb.pojoVM.FeedbackVM;
import com.mettre.modulecommon.base.ReturnType;
import com.mettre.modulecommon.enum_.StateEnum;
import com.mettre.modulecommon.enums.CustomerException;
import com.mettre.modulecommon.jwt.SecurityContextStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FeedbackService {

    @Autowired
    public UserRpcService userRpcFeign;

    @Autowired
    public FeedbackMapper feedbackMapper;

    public int deleteByPrimaryKey(Long feedbackId) {
        int type = feedbackMapper.deleteByPrimaryKey(feedbackId);
        return ReturnType.ReturnType(type, "删除失败");
    }


    public int insert(FeedbackVM feedbackVM) {
        String userId = SecurityContextStore.getContext().getUserId();
        userRpcFeign.findUserInfo(userId);
        int type = feedbackMapper.insert(new Feedback(feedbackVM, StateEnum.SUBMITTED, userId));
        return ReturnType.ReturnType(type, "插入失败");
    }

    public int updateByPrimaryKeySelective(FeedbackModifyVM feedbackVM) {
        int type = feedbackMapper.updateByPrimaryKeySelective(new Feedback(feedbackVM));
        return ReturnType.ReturnType(type, "操作失败");
    }

    public Page<Feedback> findFeedbackListPageVo(Page<Feedback> page, FeedbackSearchVM feedbackSearchVM) {
        String userId = SecurityContextStore.getContext().getUserId();
        if (null != feedbackSearchVM.getState() && !StateEnum.contains(feedbackSearchVM.getState().name())) {
            throw new CustomerException("反馈状态选择错误");
        }
        List<Feedback> feedbackList = (List<Feedback>) feedbackMapper.findFeedbackListPageVo(page, new FeedbackSearchVM(feedbackSearchVM.getState()), userId);
        page = page.setRecords(feedbackList);
        return page;
    }
}
