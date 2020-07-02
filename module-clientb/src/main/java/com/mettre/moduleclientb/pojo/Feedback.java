package com.mettre.moduleclientb.pojo;
import com.mettre.moduleclientb.pojoVM.FeedbackModifyVM;
import com.mettre.moduleclientb.pojoVM.FeedbackVM;
import com.mettre.modulecommon.enum_.StateEnum;
import lombok.Data;

import java.util.Date;

@Data
public class Feedback {

    private Long feedbackId;

    private String userId;

    private Date creationTime;

    private Date updateTime;

    private String content;//反馈内容

    private StateEnum state;

    private String userName;//昵称

    private String headAvatar;//头像

    private String phone;//手机号


    public Feedback() {

    }

    //修改状态
    public Feedback(FeedbackModifyVM feedbackModifyVM) {
        this.feedbackId = feedbackModifyVM.getFeedbackId();
        this.state = feedbackModifyVM.getState();
        this.updateTime = new Date();
    }


    //新增反馈
    public Feedback(FeedbackVM feedbackVM, StateEnum state, String userId) {
        this.userId = userId;
        this.creationTime = new Date();
        this.updateTime = new Date();
        this.content = feedbackVM.getContent();
        this.state = state;
    }
}