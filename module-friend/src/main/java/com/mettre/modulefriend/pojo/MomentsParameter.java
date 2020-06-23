package com.mettre.modulefriend.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mettre.modulecommon.enums.MomentsTypeEnum;
import lombok.Data;

import java.util.Date;

/**
 * 朋友圈列表
 */
@Data
public class MomentsParameter {

    private String momentsId;

    private String momentsTitle;

    private Date creationTime;

    private String momentsImage;//暂时只单张图

    private UserBean userBean;

    private Date date;//日期  2019-12-12

    private MomentsTypeEnum momentsType;

    @JsonIgnore
    private Boolean isShow;



}
