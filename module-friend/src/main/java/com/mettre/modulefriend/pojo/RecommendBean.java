package com.mettre.modulefriend.pojo;
import lombok.Data;

import java.util.Date;

@Data
public class RecommendBean {

    private String recommendId;
    private String userId;
    private String userName;
    private String headAvatar;
    private Boolean hasFollow;
    private Date creationTime;

    public RecommendBean(String userId) {
        this.userId = userId;
        this.creationTime = new Date();
    }
}
