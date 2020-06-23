package com.mettre.modulefriend.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Friends {

    private String myUserId;

    private Long followId;

    private String userId;

    private String userName;

    private String headAvatar;

    private Date updateTime;
}
