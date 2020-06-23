package com.mettre.modulefriend.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mettre.modulecommon.enums.DynamicTypeEnum;
import com.mettre.modulecommon.util.RandomUtil;
import com.mettre.modulefriend.pojoVM.ReplyVM;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Reply {

    private String replyId;

    private String dynamicId;//资讯id

    private String replyParentId;//评论的父id可空

    private String replyParentUserId;//被回复人的用户id

    private String dynamicUserId;//评论人的用户id

    private String secondDynamicId;//所属一级评论

    private String replyContent;

    private Date creationTime;

    @JsonIgnore
    private Boolean isShow;

    private DynamicTypeEnum dynamicType;

    private String replyParentUserName;//被回复人的昵称

    private String userName;//昵称

    private String headAvatar;//头像

    private Boolean own;

    public List<Reply> replyList;


    public Reply() {

    }

    //二级评论
    public Reply(ReplyVM replyVM, String replyParentId, String replyParentUserId, String secondDynamicId, String userId) {
        this.replyId = RandomUtil.ReplyInformationNumber();
        this.dynamicId = replyVM.getDynamicId();
        this.replyParentId = replyParentId;
        this.replyParentUserId = replyParentUserId;
        this.secondDynamicId = secondDynamicId;
//        this.dynamicUserId = replyVM.getDynamicUserId();
        this.dynamicUserId = userId;
        this.replyContent = replyVM.getReplyContent();
        this.creationTime = new Date();
        this.isShow = true;
        this.dynamicType = replyVM.getDynamicType();
    }

    //一级评论
    public Reply(ReplyVM replyVM, String userId) {
        this.replyId = RandomUtil.ReplyInformationNumber();
        this.dynamicId = replyVM.getDynamicId();
        this.secondDynamicId = replyId;
        this.dynamicUserId = userId;
        this.replyContent = replyVM.getReplyContent();
        this.creationTime = new Date();
        this.isShow = true;
        this.dynamicType = replyVM.getDynamicType();
    }
}