package com.mettre.modulefriend.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mettre.modulecommon.enums.MomentsTypeEnum;
import com.mettre.modulecommon.util.RandomUtil;
import com.mettre.modulefriend.pojoVM.MomentsVM;
import lombok.Data;

import java.util.Date;

@Data
public class Moments {

    private String momentsId;

    private String momentsTitle;

    private String publisherUserId;

    private Date creationTime;

    @JsonIgnore
    private Boolean isShow;

    private String momentsImage;

    private String publisherUserName;

    private String publisherHeadAvatar;

    private MomentsTypeEnum momentsType;

    public Moments() {

    }

    public Moments(MomentsVM momentsVM, String userId) {
        this.momentsId = RandomUtil.generateUserId();
        this.momentsTitle = momentsVM.getMomentsTitle();
        this.publisherUserId = userId;
        this.creationTime = new Date();
        this.isShow = true;
        this.momentsImage = momentsVM.getMomentsImage();
        this.momentsType = momentsVM.getMomentsType();
    }
}