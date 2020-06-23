package com.mettre.modulefriend.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mettre.modulefriend.pojoVM.FollowVM;
import lombok.Data;

import java.util.Date;


@Data
public class Follow {

    private Long followId;

    private String userId;

    private String followedUser;

    private Boolean status;

    private Date creationTime;

    private Date updateTime;

    private String userName;

    private String followedUserName;

    private String headAvatar;

    @JsonIgnore
    private String userId2;//判断是否互关（为空则不互关）

    private Boolean eachOther; // true:互关

    public Follow() {

    }


    //新增关注
    public Follow(FollowVM followVM, String userId) {
        this.userId = userId;
        this.followedUser = followVM.getFollowedUser();
        this.status = true;
        this.creationTime = new Date();
        this.updateTime = new Date();
    }


}